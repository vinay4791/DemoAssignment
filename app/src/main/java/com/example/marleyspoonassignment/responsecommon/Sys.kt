package com.example.marleyspoonassignment.responsecommon

import com.example.marleyspoonassignment.common.ContentType

data class Sys(val type : String,
               val id : String,
               val locale : String,
               val revision : Integer,
               val contentType : ContentType
)