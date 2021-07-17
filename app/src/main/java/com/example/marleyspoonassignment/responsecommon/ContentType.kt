package com.example.marleyspoonassignment.common

import com.squareup.moshi.Json

data class ContentType(
    @Json(name = "sys")
    val contentTypeSys: ContentTypeSys
)

data class ContentTypeSys(
    @Json(name = "type")
    val type: String,
    @Json(name = "linkType")
    val linkType: String,
    @Json(name = "id")
    val id: String
)