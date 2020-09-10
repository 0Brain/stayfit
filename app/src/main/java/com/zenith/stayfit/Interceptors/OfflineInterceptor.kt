package com.zenith.stayfit.Interceptors

import com.zenith.stayfit.FitApplication
import com.zenith.stayfit.commons.Constants.HEADER_CACHE_CONTROL
import com.zenith.stayfit.commons.Constants.HEADER_PRAGMA
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit


/**
 * This interceptor will be called both if the network is available and if the network is not available
 * @return
 */

class OfflineInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        // prevent caching when network is on. For that we use the "networkInterceptor"
        /*
           *  If there is no Internet, get the cache that was stored 7 days ago.
           *  If the cache is older than 7 days, then discard it,
           *  and indicate an error in fetching the response.
           *  The 'max-stale' attribute is responsible for this behavior.
           *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
           */
        if (!FitApplication.hasNetwork()) {
            val cacheControl = CacheControl.Builder()
                .maxStale(7, TimeUnit.DAYS)
                .build()
            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }

        return chain.proceed(request)
    }

}