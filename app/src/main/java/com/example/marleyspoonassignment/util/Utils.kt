package com.example.marleyspoonassignment.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

const val WIFI = "WIFI"
const val MOBILE = "MOBILE"

class Utils(private val appContext: Context) {

    fun hasInternet(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                ?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            var haveConnectedWifi = false
            var haveConnectedMobile = false
            val netInfo = connectivityManager.allNetworkInfo
            for (ni in netInfo) {
                if (ni.typeName.equals(WIFI, ignoreCase = true)) if (ni.isConnected) haveConnectedWifi = true
                if (ni.typeName.equals(MOBILE, ignoreCase = true)) if (ni.isConnected) haveConnectedMobile = true
            }
            return haveConnectedWifi || haveConnectedMobile
        }
    }



}

