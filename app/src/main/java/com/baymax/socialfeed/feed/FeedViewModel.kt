package com.baymax.socialfeed.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baymax.socialfeed.data.entity.PostData
import com.baymax.socialfeed.data.models.PostResponse
import com.baymax.socialfeed.util.DispatcherProvider
import com.baymax.socialfeed.util.Resource
import com.baymax.socialfeed.util.toPostData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by abhinav on 18/11/21.
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FeedRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {

    sealed class FeedEvent{
        class Success(val postsData: List<PostData>): FeedEvent()
        class Failure(val errorText: String): FeedEvent()
        object Loading: FeedEvent()
        object Empty: FeedEvent()
    }

    private val _feed = MutableStateFlow<FeedEvent>(FeedEvent.Empty)
    val feed: StateFlow<FeedEvent> = _feed

    fun getFeed(){
        viewModelScope.launch(dispatchers.io){
            _feed.value = FeedEvent.Loading
            when(val response = repository.getPosts()){
                is Resource.Success -> {
                    if(response.data == null){
                        _feed.value = FeedEvent.Failure("Something went wrong")
                    }else {
                        val posts = filterFeedToPostData(response.data)
                        _feed.value = FeedEvent.Success(posts)
                    }
                }
                is Resource.Error -> {
                    _feed.value = FeedEvent.Failure(response.message!!)
                }
                else -> Unit
            }
        }
    }

    private fun filterFeedToPostData(postResponse: PostResponse): List<PostData> {
        val posts = ArrayList<PostData>()
        Log.d("Flow Posts", postResponse.data.toString())
        for(data in postResponse.data){
            posts.add(data.toPostData())
        }
        return posts
    }


}