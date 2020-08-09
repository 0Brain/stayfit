package com.zenith.stayfit.ui.login.network

import com.zenith.stayfit.BuildConfig
import com.zenith.stayfit.ui.login.model.login.AuthenticationLoginRequest
import com.zenith.stayfit.ui.login.model.login.LoginBody
import com.zenith.stayfit.ui.login.model.register.AuthenticationRegisterResponse
import com.zenith.stayfit.ui.login.model.register.RegisterBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface AuthenticationService {
    @POST(BuildConfig.SIGN_IN)
    @Headers("Content-Type: application/json")
    fun loginUser(
        @Body loginBody: LoginBody
    ): Call<AuthenticationLoginRequest>

    @POST(BuildConfig.SIGN_UP)
    @Headers("Content-Type: application/json")
    suspend fun registerUser(
        @Body registerBody: RegisterBody
    ): Response<AuthenticationRegisterResponse>
}