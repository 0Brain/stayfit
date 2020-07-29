package com.zenith.stayfit.ui.login.network


import com.zenith.stayfit.ui.login.model.AuthenticationResponse
import com.zenith.stayfit.ui.login.model.AuthenticationResponseDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface LoginService {
    @GET("/user")
    fun getUser(
    ): Call<AuthenticationResponse>

}