package com.e.weatherapp.model.weather

import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("type") val type : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("country") val country : String,
    @SerializedName("sunrise") val sunrise : Int,
    @SerializedName("sunset") val sunset : Int,
    @SerializedName("message") val message : Double
)