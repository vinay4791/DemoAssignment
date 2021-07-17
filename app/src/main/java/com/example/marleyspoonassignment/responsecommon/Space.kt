package com.example.marleyspoonassignment.responsecommon

import com.squareup.moshi.Json

data class Space(
    @Json(name = "sys")
    val contentTypeSys: SpaceSys
)

data class SpaceSys(
    @Json(name = "type")
    val type: String,
    @Json(name = "linkType")
    val linkType: String,
    @Json(name = "id")
    val id: String
)