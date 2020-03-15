package com.e.weatherapp.repositories

import androidx.lifecycle.MutableLiveData
import com.e.weatherapp.model.city.CityDataModel
import com.e.weatherapp.network.CityApi
import com.e.weatherapp.network.NetworkConstants
import com.e.weatherapp.network.NetworkConstants.Companion.BASE_CITY_URL
import com.e.weatherapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesRepository(private val retrofit: RetrofitClient) {
    private lateinit var api: CityApi

    fun getCityData(city: String): MutableLiveData<MutableList<CityDataModel>> {

        api = retrofit.retrofit(NetworkConstants.BASE_CITY_URL).create(CityApi::class.java)
//        api = RetrofitClient.instanceRetrofit(BASE_CITY_URL)!!
        val data = MutableLiveData<MutableList<CityDataModel>>()
        api.getCityData(city)
            .enqueue(object : Callback<MutableList<CityDataModel>> {
                override fun onResponse(
                    call: Call<MutableList<CityDataModel>>,
                    response: Response<MutableList<CityDataModel>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<MutableList<CityDataModel>>, t: Throwable) {
                    data.value = null
                }
            })
        return data
    }
}