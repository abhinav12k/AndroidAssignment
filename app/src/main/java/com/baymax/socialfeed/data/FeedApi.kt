package com.baymax.socialfeed.data

import com.baymax.socialfeed.data.models.PostResponse
import retrofit2.http.GET

/**
 * Created by abhinav on 18/11/21.
 */
interface FeedApi {

    @GET("/posts")
    suspend fun getPosts(): PostResponse

}