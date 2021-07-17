package com.example.marleyspoonassignment.common

data class ContentType(
    val sys: ContentTypeSys
)

data class ContentTypeSys(
    val type: String,
    val linkType: String,
    val id: String
)