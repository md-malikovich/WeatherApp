package com.e.weatherapp.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.e.weatherapp.base.BaseViewModel
import com.e.weatherapp.model.city.CityDataModel
import com.e.weatherapp.repositories.CitiesRepository
import kotlinx.coroutines.launch

class CityViewModel(private val cRepository: CitiesRepository) : BaseViewModel() {

    var cities: MutableLiveData<MutableList<CityDataModel>> = MutableLiveData()
    fun getCityData(city: String) {
        viewModelScope.launch {
            getCityDataFromNetwork(city)
            loading.value = false
        }
    }

    private suspend fun getCityDataFromNetwork(city: String) {
        cities = cRepository.getCityData(city)
    }
}