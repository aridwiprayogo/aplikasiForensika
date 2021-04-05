package com.project.forensika.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "access_token")
    val accessToken: String,
    val user: User
)
