package com.example.marleyspoonassignment.recipelist.backend

import com.example.marleyspoonassignment.responsecommon.Sys

data class ResponseBaseItem(
    val sys: Sys,
    val fields: Fields
)

data class Fields(
    val name : String,
    val title: String,
    val description: String,
    val photo: Photo,
    val file: File,
    val chef: Chef,
    val tags: List<Sys>
)

data class Photo(val sys: Sys)

data class File(val url: String)

data class Chef(val sys: Sys)