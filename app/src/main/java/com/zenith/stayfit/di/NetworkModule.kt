package com.zenith.stayfit.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zenith.stayfit.FitApplication
import com.zenith.stayfit.Interceptors.HttpRequestInterceptor
import com.zenith.stayfit.Interceptors.NetworkInterceptor
import com.zenith.stayfit.Interceptors.OfflineInterceptor
import com.zenith.stayfit.commons.Constants
import com.zenith.stayfit.ui.diary.network.FoodService
import com.zenith.stayfit.ui.login.network.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    private const val cacheSize = (5 * 1024 * 1024).toLong() //5MB

    @Singleton
    @Provides
    fun getCache(): Cache {
        return Cache(File(FitApplication.getInstance()!!.cacheDir, "cacheIdentifier"), cacheSize)
    }

    @Singleton
    @Provides
    fun provideOkHttp(myCache: Cache): OkHttpClient {
        return OkHttpClient
            .Builder()
            .cache(myCache)
            .addInterceptor(HttpRequestInterceptor())
            .addInterceptor(OfflineInterceptor())
            .addNetworkInterceptor(NetworkInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshiInstance(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(Constants.INGREDIENTS_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Singleton
    @Provides
    fun provideFoodApi(retrofit: Retrofit): FoodService {
        return retrofit.create(FoodService::class.java)
    }
}
