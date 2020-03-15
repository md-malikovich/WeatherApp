package com.e.weatherapp.ui.city

import androidx.lifecycle.MutableLiveData
import com.e.weatherapp.base.BaseViewModel
import com.e.weatherapp.model.city.CityDataModel
import com.e.weatherapp.repositories.CitiesRepository

class CityViewModel(private val cRepository: CitiesRepository) : BaseViewModel() {

//    fun getCityData(capital: String): MutableLiveData<List<CityDataModel>> {
//        return cRepository.getCityData(capital)
//    }

    var cities: MutableLiveData<MutableList<CityDataModel>> = MutableLiveData()
    fun getCityData(city: String) {
        loading.value = false
        cities = cRepository.getCityData(city)
    }
}