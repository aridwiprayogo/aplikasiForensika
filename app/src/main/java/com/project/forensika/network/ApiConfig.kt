package com.project.forensika.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://skripsi.farhandev.my.id"

object ApiConfig {

    private fun provideOkHttpClient(): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
    }

    fun create(): ApiService {
        return provideRetrofit().create(ApiService::class.java)
    }

    private fun provideRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}
