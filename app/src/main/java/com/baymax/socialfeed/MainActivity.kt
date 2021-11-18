package com.baymax.socialfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.baymax.socialfeed.databinding.ActivityMainBinding
import com.baymax.socialfeed.feed.FeedViewModel
import com.baymax.socialfeed.feed.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postAdapter = PostAdapter()
        binding.rvPosts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPosts.adapter = postAdapter

        //fetch feed
        viewModel.getFeed()

        lifecycleScope.launchWhenStarted {
            viewModel.feed.collect { event ->
                when (event) {
                    is FeedViewModel.FeedEvent.Success -> {
                        Log.d("Flow Success", event.postsData.toString())
                        postAdapter.submitList(event.postsData)
                    }
                    is FeedViewModel.FeedEvent.Failure -> {
                        Log.d("Flow Error", event.errorText)
                        Toast.makeText(
                            this@MainActivity,
                            "Sorry we are not able to fetch posts at the moment :(",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is FeedViewModel.FeedEvent.Loading -> {
                        Log.d("Flow Loading", "Loading...")
                    }
                    else -> Unit
                }
            }
        }
    }
}