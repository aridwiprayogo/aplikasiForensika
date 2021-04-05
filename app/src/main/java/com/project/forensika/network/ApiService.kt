package com.project.forensika.network

import com.project.forensika.model.CheckTools
import com.project.forensika.model.History
import com.project.forensika.model.RecommendationTools
import com.project.forensika.model.UserResponse
import com.project.forensika.model.payload.CheckToolsPayload
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("/api/register")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun register(@Field("name") username: String,
                 @Field("email") email: String,
                 @Field("password") sandi: String,
                 @Field("password_confirmation") konsandi: String
    ): Call<Map<String,Any>>

    @POST("/api/login")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun login(@Field("email") username: String,
              @Field("password") sandi: String
    ): Call<UserResponse>

    @GET("/api/recommendation-tools")
    fun getRecommendationsTools(
            @HeaderMap header: Map<String,String>
    ): Call<RecommendationTools>

    @GET("/api/histories")
    fun getHistories(
            @HeaderMap header: Map<String, String>
    ): Call<History>

    @POST("/api/check-tools")
    fun checkTools(
            @HeaderMap header: Map<String, String>,
            @Body request: CheckToolsPayload
    ) : Call<CheckTools>

    @POST("/api/update")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun ubahProfile(
            @HeaderMap header: Map<String, String>,
            @Field("email") username: String,
            @Field("password") sandi: String
    ): Call<UserResponse>
}
