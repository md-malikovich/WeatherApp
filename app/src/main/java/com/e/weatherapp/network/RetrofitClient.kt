package com.e.weatherapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val httplLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient().newBuilder().addInterceptor(httplLogging)
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .build()

    fun retrofit(url: String) = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

//    fun instanceRetrofit(baseUrl: String): ApiService? {
//        val okHttpClient = OkHttpClient().newBuilder()
//            .connectTimeout(40, TimeUnit.SECONDS)
//            .readTimeout(40, TimeUnit.SECONDS)
//            .writeTimeout(40, TimeUnit.SECONDS)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(baseUrl)
//            .client(okHttpClient)
//            .build()
//
//        return retrofit.create(ApiService::class.java)
//    }
}