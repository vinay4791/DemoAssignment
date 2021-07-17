package com.example.marleyspoonassignment.util

import android.util.TypedValue
import android.view.View

fun View?.pxIntoDp(margin: Float): Int {
    return this?.let {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                margin,
                it.context.resources.displayMetrics)
                .toInt()
    } ?: 0
}

fun View?.isVisible(isVisible: Boolean = true) {
    this?.apply {
        visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}
