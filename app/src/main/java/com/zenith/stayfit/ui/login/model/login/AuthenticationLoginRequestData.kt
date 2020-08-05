package com.zenith.stayfit.ui.login.model.login


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationLoginRequestData(
    @Json(name = "isSuccess")
    val isSuccess: String,
    @Json(name = "message")
    val message: String
)