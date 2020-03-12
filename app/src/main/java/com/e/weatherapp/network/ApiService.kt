package com.e.weatherapp.network

import com.e.weatherapp.model.WeatherMainModel
import com.e.weatherapp.model.city.CityDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call

const val WEATHER_KEY = "c6e381d8c7ff98f0fee43775817cf6ad"
interface ApiService {

    @GET("data/2.5/weather")
    fun getWeatherData(@Query("units") units: String,
                       @Query("lat") lat: Double,
                       @Query("lon") lon: Double,
                       @Query("appId") appId: String) : Call<WeatherMainModel>

    @GET("rest/v2/capital/{city}")
    fun getCityData(@Path("city") capital: String) : Call<List<CityDataModel>>
}