package com.e.weatherapp.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.weatherapp.model.weather.WeatherMainModel
import com.e.weatherapp.repositories.WeatherRepository

class MapViewModel(private val wRepository: WeatherRepository) : ViewModel() {
    lateinit var liveData: MutableLiveData<WeatherMainModel>
    fun getWeatherData(units: String, lat: Double, lon: Double){
        liveData = wRepository.getWeatherData(units, lat, lon)
    }
}