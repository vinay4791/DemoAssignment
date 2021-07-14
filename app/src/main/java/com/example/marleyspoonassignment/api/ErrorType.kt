package com.example.marleyspoonassignment.api

import androidx.annotation.StringRes
import com.example.marleyspoonassignment.R

enum class ErrorType(@StringRes val errorCauseString: Int) {
    NO_INTERNET_CONNECTION(R.string.no_internet_connection_message),
    SERVER(R.string.server_error),
    UNKNOWN(R.string.unknown_error);
    fun value(): Int {
        return errorCauseString
    }
}