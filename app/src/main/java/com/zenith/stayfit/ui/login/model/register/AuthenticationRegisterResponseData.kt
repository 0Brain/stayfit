package com.zenith.stayfit.ui.login.model.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationRegisterResponseData(
    @Json(name = "isSuccess")
    val isSuccess: String,
    @Json(name = "message")
    val message: String
)