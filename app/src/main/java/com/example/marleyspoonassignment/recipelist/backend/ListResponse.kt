package com.example.marleyspoonassignment.recipelist.backend

import com.example.marleyspoonassignment.responsecommon.Sys

data class ListResponse(
    val total: Integer,
    val skip: Integer,
    val limit: Integer,
    val sys: Sys,
    val items: List<ResponseBaseItem>,
    val includes: IncludesResponse
)