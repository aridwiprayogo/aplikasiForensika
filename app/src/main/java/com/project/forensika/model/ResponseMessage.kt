package com.project.forensika.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseMessage(
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: String
)