package com.e.weatherapp.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.weatherapp.model.city.CityDataModel
import com.e.weatherapp.repositories.CitiesRepository

class CityViewModel(private val cRepository: CitiesRepository) : ViewModel() {

    fun getCityData(capital: String): MutableLiveData<List<CityDataModel>> {
        return cRepository.getCityData(capital)
    }
}