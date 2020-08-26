
package com.zenith.stayfit.ui.login.model.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationLoginRequest(
  @Json(name = "code")
  val code: String,
  @Json(name = "data")
  val data: AuthenticationLoginRequestData
)
