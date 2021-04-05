package com.project.forensika.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class CheckTools(
    val result: List<Result>,
    val status: String
) {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Result(
        val id: Int,
        @Json(name = "id_aplikasi")
        val idAplikasi: Int,
        @Json(name = "nama_aplikasi")
        val namaAplikasi: String,
        @Json(name = "nama_aturan")
        val namaAturan: String
    ):Parcelable
}