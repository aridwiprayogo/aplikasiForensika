package com.project.forensika.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistoryDetail(
    @Json(name = "result")
    val result: Result,
    @Json(name = "status")
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "detail")
        val detail: Detail,
        @Json(name = "id")
        val id: Int,
        @Json(name = "id_aplikasi")
        val idAplikasi: Int,
        @Json(name = "id_aturan")
        val idAturan: String,
        @Json(name = "nama_aplikasi")
        val namaAplikasi: String,
        @Json(name = "nama_aturan")
        val namaAturan: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Detail(
            @Json(name = "fungsionalitas")
            val fungsionalitas: List<String>,
            @Json(name = "karakteristik")
            val karakteristik: List<Karakteristik>,
            @Json(name = "keterangan")
            val keterangan: String
        ) {
            @JsonClass(generateAdapter = true)
            data class Karakteristik(
                @Json(name = "jenis")
                val jenis: String,
                @Json(name = "value")
                val value: String
            )
        }
    }
}
