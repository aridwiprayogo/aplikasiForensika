package com.project.forensika.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserProfile(
    @Json(name = "result")
    val result: Result,
    @Json(name = "status")
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "admin")
        val admin: String,
        @Json(name = "created_at")
        val createdAt: String,
        @Json(name = "email")
        val email: String,
        @Json(name = "email_verified_at")
        val emailVerifiedAt: Any?,
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "role")
        val role: String?,
        @Json(name = "updated_at")
        val updatedAt: String
    )
}