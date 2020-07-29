package com.zenith.stayfit.di

import com.zenith.stayfit.Interceptors.HttpRequestInterceptor
import com.zenith.stayfit.ui.login.network.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp():OkHttpClient{
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit):LoginService{
        return retrofit.create(LoginService::class.java)
    }
}