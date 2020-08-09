
package com.zenith.stayfit.ui.login.model.register

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationRegisterResponse(
  @Json(name = "code")
  val code: String,
  @Json(name = "data")
  val data: AuthenticationRegisterResponseData
)
