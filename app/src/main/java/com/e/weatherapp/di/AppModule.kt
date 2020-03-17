package com.e.weatherapp.di

import androidx.room.Room
import com.e.weatherapp.db.WeatherDatabase
import com.e.weatherapp.network.RetrofitClient
import com.e.weatherapp.repositories.CitiesRepository
import com.e.weatherapp.repositories.WeatherRepository
import com.e.weatherapp.ui.city.CityViewModel
import com.e.weatherapp.ui.map.MapViewModel
import com.e.weatherapp.ui.weather_bottom.WeatherBottomViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        RetrofitClient()
    }

    single {
        Room.databaseBuilder(androidApplication(), WeatherDatabase::class.java, "WEATHER_DB")
            .build()
    }

    single { get<WeatherDatabase>().favoriteDao }

    factory {
        WeatherRepository(get(), get())
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

    viewModel {
        WeatherBottomViewModel(get())
    }
}