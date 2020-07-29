package com.zenith.stayfit.ui.login.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationResponseDetails(
    @Json(name = "name")
    val name: String?,
    @Json(name = "password")
    val password: String?,
    @Json(name = "public_id")
    val publicId: String?
)