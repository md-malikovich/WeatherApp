package com.e.weatherapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.weatherapp.model.weather.WeatherMainModel
import com.e.weatherapp.repositories.WeatherRepository

class MapViewModel(private val wRepository: WeatherRepository) : ViewModel() {

    private var loading: Boolean = false
    fun getWeatherData(units: String, lat: Double, lon: Double): LiveData<WeatherMainModel> {
        return wRepository.getWeatherData(units, lat, lon)
    }
}