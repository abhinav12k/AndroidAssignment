package com.baymax.socialfeed.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "pagination")
    val pagination: Pagination
)