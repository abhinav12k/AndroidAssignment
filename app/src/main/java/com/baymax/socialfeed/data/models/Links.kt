package com.baymax.socialfeed.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "current")
    val current: String,
    @Json(name = "next")
    val next: String,
    @Json(name = "previous")
    val previous: Any?
)