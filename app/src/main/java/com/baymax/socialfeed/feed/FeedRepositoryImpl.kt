package com.baymax.socialfeed.feed

import android.util.Log
import com.baymax.socialfeed.data.FeedApi
import com.baymax.socialfeed.data.models.PostResponse
import com.baymax.socialfeed.util.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by abhinav on 18/11/21.
 */
class FeedRepositoryImpl @Inject constructor(
    private val api: FeedApi
) : FeedRepository {
    override suspend fun getPosts(): Resource<PostResponse> {
        return try{
            val response = api.getPosts()
            val result = response.body()
            if(response.isSuccessful && result!=null){
                Resource.Success(result)
            }else{
//                Log.d("Flow Repo","Message ${response.message()} ${response.code()} ${response.raw()}")
//                Log.d("Flow Repo","Failure!!!")
                Resource.Error(response.message())
            }
        }catch (e: Exception){
            Resource.Error(e.message)
        }
    }
}