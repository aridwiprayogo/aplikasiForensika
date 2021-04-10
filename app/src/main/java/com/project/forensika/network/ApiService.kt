package com.project.forensika.network

import com.project.forensika.model.*
import com.project.forensika.model.payload.CheckToolsPayload
import okhttp3.ResponseBody
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
    @Headers("Accept: application/json")
    fun getRecommendationsTools(
            @HeaderMap header: Map<String,String>
    ): Call<RecommendationTools>

    @GET("/api/histories")
    @Headers("Accept: application/json")
    fun getHistories(
            @HeaderMap header: Map<String, String>
    ): Call<History>

    @GET("/api/history/{id_history}")
    @Headers("Accept: application/json")
    fun getHistoryDetail(
            @HeaderMap header: Map<String, String>,
            @Path("id_history") idHistory: Int
    ): Call<HistoryDetail>

    @GET("/api/keterangan/{id_keterangan}")
    @Headers("Accept: application/json")
    fun getHistoryKeterangan(
            @HeaderMap header: Map<String, String>,
            @Path("id_keterangan") idKeterangan: Int
    ): Call<ResponseBody>

    @GET("/api/history/delete/{id_history}")
    @Headers("Accept: application/json")
    fun deleteHistory(
            @HeaderMap header: Map<String, String>,
            @Path("id_history") idHistory: Int
    ): Call<Map<String,String>>

    @POST("/api/check-tools")
    fun checkTools(
            @HeaderMap header: Map<String, String>,
            @Body request: CheckToolsPayload
    ) : Call<CheckTools>

    @GET("/api/profile")
    @Headers("Accept: application/json")
    fun getProfile(
            @HeaderMap header: Map<String, String>,
    ): Call<UserProfile>

    @POST("/api/profile/update")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun ubahProfile(
            @HeaderMap header: Map<String, String>,
            @Field("name") username: String,
            @Field("email") email: String
    ): Call<UserProfile>

    @POST("/api/profile/update-password")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun ubahPasswordProfile(
            @HeaderMap header: Map<String, String>,
            @Field("current_password") currentPassword: String,
            @Field("password") password: String,
            @Field("password_confirmation") konfirmasiPassword: String
    ): Call<ResponseMessage>

    @GET("/api/profile")
    @Headers("Accept: application/json")
    fun logout(header: Map<String, String>): Call<Map<String, Any>>
}
