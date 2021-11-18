package com.baymax.socialfeed.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostResponse(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "meta")
    val meta: Meta
)