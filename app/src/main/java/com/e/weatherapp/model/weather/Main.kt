package com.e.weatherapp.model.weather

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp") val temp : Double,
    @SerializedName("feels_like") val feels_like : Double,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("sea_level") val sea_level : Double,
    @SerializedName("grnd_level") val grnd_level : Double
)