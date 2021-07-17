package com.example.marleyspoonassignment.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.example.marleyspoonassignment.R

import kotlinx.android.synthetic.main.loading_screen.view.*

class FullScreenLoadingView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_screen, this, true)
        hideLoading()
        setOnClickListener { }
        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.FullScreenLoadingView, 0, 0)
            val loaderSizeOrdinal = typedArray.getInt(R.styleable.FullScreenLoadingView_loader_size,
                    0)
            when (LoaderSize.from(loaderSizeOrdinal)) {
                LoaderSize.SMALL -> setupLoaderParams()
                LoaderSize.NORMAL -> {
                    // no op
                }
            }
            typedArray.recycle()
        }
    }

    private fun setupLoaderParams() {
        val params = loading_screen.layoutParams
        params.apply {
            height = pxIntoDp(30F)
            width = pxIntoDp(30F)
        }
        loading_screen.layoutParams = params
    }

    fun showLoading(@DrawableRes drawableInt: Int) {
        background = ContextCompat.getDrawable(context, drawableInt)
        visibility = View.VISIBLE
        loading_screen.playAnimation()
    }

    fun hideLoading() {
        loading_screen.clearAnimation()
        visibility = View.GONE
    }

    enum class LoaderSize {
        NORMAL,
        SMALL;

        companion object {
            fun from(ordinal: Int): LoaderSize {
                return values()[ordinal]
            }
        }
    }
}
