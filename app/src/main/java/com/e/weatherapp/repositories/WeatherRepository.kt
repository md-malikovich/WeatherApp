package com.e.weatherapp.repositories

import androidx.lifecycle.MutableLiveData
import com.e.weatherapp.db.FavoriteCitiesDao
import com.e.weatherapp.model.city.FavoriteCities
import com.e.weatherapp.model.weather.WeatherMainModel
import com.e.weatherapp.network.NetworkConstants
import com.e.weatherapp.network.RetrofitClient
import com.e.weatherapp.network.WEATHER_KEY
import com.e.weatherapp.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val retrofit: RetrofitClient, private val dao: FavoriteCitiesDao) {
    private lateinit var api: WeatherApi

    fun getWeatherData(units: String, lat: Double, lon: Double): MutableLiveData<WeatherMainModel> {
        api = retrofit.retrofit(NetworkConstants.BASE_URL).create(WeatherApi::class.java)
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

    suspend fun insertToDb(lng: Double, lat: Double) {
        dao.insertFavorite(
            FavoriteCities(
                id = 1,
                latitude = lat,
                longitude = lng)
        )
    }
}