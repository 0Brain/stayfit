package com.zenith.stayfit.Interceptors

import com.zenith.stayfit.commons.Constants.HEADER_CACHE_CONTROL
import com.zenith.stayfit.commons.Constants.HEADER_PRAGMA
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * This interceptor will be called ONLY if the network is available
 * @return
 */

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        /*
        *  If there is Internet, get the cache that was stored 15 seconds ago.
        *  If the cache is older than 15 seconds, then discard it,
        *  and indicate an error in fetching the response.
        *  The 'max-age' attribute is responsible for this behavior.
        */
        val cacheControl = CacheControl.Builder()
            .maxAge(15, TimeUnit.SECONDS)
            .build()

        return response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }
}