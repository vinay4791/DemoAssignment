package com.example.marleyspoonassignment.recipelist.backend

import com.example.marleyspoonassignment.responsecommon.Sys

data class ResponseBaseItem(
    val sys: Sys,
    val fields: Fields
)

data class Fields(
    val title: String,
    val description: String,
    val photo: Photo,
    val file: File
)

data class Photo(val sys: Sys)

data class File(val url: String)