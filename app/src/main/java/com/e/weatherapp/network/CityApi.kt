package com.e.weatherapp.network

import com.e.weatherapp.model.city.CityDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface CityApi {
    @GET("rest/v2/capital/{city}")
    fun getCityData(@Path("city") capital: String): Call<MutableList<CityDataModel>>
}
