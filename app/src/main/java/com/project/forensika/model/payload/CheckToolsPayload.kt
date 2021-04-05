package com.project.forensika.model.payload


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckToolsPayload(
    @Json(name = "id_fungsionalitas")
    val idFungsionalitas: Int,
    val karakteristik: List<Int>
)