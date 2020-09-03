package com.zenith.stayfit.ui.diary.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "carbohydrates")
    val carbohydrates: String,
    @Json(name = "carbohydrates_sugar")
    val carbohydratesSugar: String?,
    @Json(name = "creation_date")
    val creationDate: String,
    @Json(name = "energy")
    val energy: Int,
    @Json(name = "fat")
    val fat: String,
    @Json(name = "fat_saturated")
    val fatSaturated: String?,
    @Json(name = "fibres")
    val fibres: Any?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "language")
    val language: Int,
    @Json(name = "license")
    val license: Int,
    @Json(name = "license_author")
    val licenseAuthor: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "protein")
    val protein: String,
    @Json(name = "sodium")
    val sodium: String?,
    @Json(name = "status")
    val status: String,
    @Json(name = "update_date")
    val updateDate: String
)