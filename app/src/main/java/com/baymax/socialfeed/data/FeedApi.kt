package com.baymax.socialfeed.data

import com.baymax.socialfeed.data.models.PostResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by abhinav on 18/11/21.
 */
interface FeedApi {

    @GET("/public/v1/posts")
    suspend fun getPosts(): Response<PostResponse>

    companion object {
        const val BASE_URL = "https://gorest.co.in/"
    }

}