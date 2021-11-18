package com.baymax.socialfeed.feed

import com.baymax.socialfeed.data.models.PostResponse
import com.baymax.socialfeed.util.Resource

/**
 * Created by abhinav on 18/11/21.
 */
interface FeedRepository {
    suspend fun getPosts(): Resource<PostResponse>
}