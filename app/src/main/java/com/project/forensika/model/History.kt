package com.project.forensika.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class History(
    val result: List<Result>?,
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        val id: Int,
        @Json(name = "id_aplikasi")
        val idAplikasi: Int,
        @Json(name = "id_aturan")
        val idAturan: Int,
        @Json(name = "nama_aplikasi")
        val namaAplikasi: String,
        @Json(name = "nama_aturan")
        val namaAturan: String,
        @Json(name = "foto_aplikasi")
        val fotoAplikasi: String,
        @Json(name = "created_at")
        val createdAt: String,
    )
}
