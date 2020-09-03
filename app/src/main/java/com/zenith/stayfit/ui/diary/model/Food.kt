package com.zenith.stayfit.ui.diary.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Food(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: Any?,
    @Json(name = "previous")
    val previous: Any?,
    @Json(name = "results")
    val results: List<Result>
)