package com.project.forensika.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecommendationTools(
    val result: List<Result>,
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        val aplikasi: String,
        val aturan: String,
        val foto: String?,
        @Json(name = "jumlah_pengecekan")
        val jumlahPengecekan: Int
    )
}