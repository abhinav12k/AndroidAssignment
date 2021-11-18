package com.baymax.socialfeed.util

import com.baymax.socialfeed.data.entity.PostData
import com.baymax.socialfeed.data.models.Data

/**
 * Created by abhinav on 18/11/21.
 */

fun Data.toPostData() = PostData(
    body = this.body,
    id = this.id,
    userId = this.userId,
    title = this.title
)
