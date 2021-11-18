package com.baymax.socialfeed.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pagination(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "links")
    val links: Links,
    @Json(name = "page")
    val page: Int,
    @Json(name = "pages")
    val pages: Int,
    @Json(name = "total")
    val total: Int
)