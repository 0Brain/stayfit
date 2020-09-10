package com.zenith.stayfit

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class FitApplication : Application() {
    companion object {
        private var instance: FitApplication? = null
        fun getInstance(): FitApplication? {
            return instance
        }

        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected()
        }
    }


    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
    }
    @Suppress("DEPRECATION")
    private fun isNetworkConnected(): Boolean {
        var isConnected = false // Initial Value
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities: Network = connectivityManager.activeNetwork ?: return false
            val activeNetwork: NetworkCapabilities =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            isConnected = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                activeNetworkInfo?.run {
                    isConnected = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return isConnected
    }
}
