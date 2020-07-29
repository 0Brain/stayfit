package com.zenith.stayfit.ui.login.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationResponse(
    @Json(name = "users")
    val users: List<AuthenticationResponseDetails>
)