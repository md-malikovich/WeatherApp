package com.e.weatherapp.di

import com.e.weatherapp.network.RetrofitClient
import com.e.weatherapp.repositories.CitiesRepository
import com.e.weatherapp.repositories.WeatherRepository
import com.e.weatherapp.ui.city.CityViewModel
import com.e.weatherapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        RetrofitClient()
    }

    factory {
        WeatherRepository(get())
    }

    factory {
        CitiesRepository(get())
    }

    viewModel {
        MapViewModel(get())
    }

    viewModel {
        CityViewModel(get())
    }
}