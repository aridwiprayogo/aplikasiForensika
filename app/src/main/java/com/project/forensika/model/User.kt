package com.project.forensika.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val admin: Int,
    @Json(name = "created_at")
    val createdAt: String,
    val email: String,
    @Json(name = "email_verified_at")
    val emailVerifiedAt: String?,
    val id: Int,
    val name: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    val role: String?
)