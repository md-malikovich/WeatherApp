package com.e.weatherapp.repositories

import androidx.lifecycle.MutableLiveData
import com.e.weatherapp.model.WeatherMainModel
import com.e.weatherapp.network.ApiService
import com.e.weatherapp.network.RetrofitClient
import com.e.weatherapp.network.WEATHER_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_URL = "http://api.openweathermap.org/"
class WeatherRepository() {
    private lateinit var api: ApiService

    fun getWeatherData(units: String, lat: Double, lon: Double): MutableLiveData<WeatherMainModel> {
        api = RetrofitClient.instanceRetrofit(BASE_URL)!!
        val data = MutableLiveData<WeatherMainModel>()
        api.getWeatherData(units, lat, lon, WEATHER_KEY)
            .enqueue(object : Callback<WeatherMainModel> {
                override fun onResponse(
                    call: Call<WeatherMainModel>,
                    response: Response<WeatherMainModel>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<WeatherMainModel>, t: Throwable) {
                    data.value = null
                }
            })
        return data
    }
}