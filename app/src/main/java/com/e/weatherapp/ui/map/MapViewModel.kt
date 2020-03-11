package com.e.weatherapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.weatherapp.model.WeatherMainModel
import com.e.weatherapp.repositories.WeatherRepository

class MapViewModel(private val wRepository: WeatherRepository) : ViewModel() {

    fun getWeatherData(units: String, lat: String, lon: String): LiveData<WeatherMainModel> {
        return wRepository.getWeatherData(units, lat, lon)
    }
}