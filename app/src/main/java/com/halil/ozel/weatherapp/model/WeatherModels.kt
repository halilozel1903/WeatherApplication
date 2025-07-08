package com.halil.ozel.weatherapp.model

data class WeatherResponse(
    val current: Current,
    val daily: List<Daily>
)

data class Current(
    val temp: Double,
    val weather: List<WeatherDesc>
)

data class Daily(
    val dt: Long,
    val temp: Temp,
    val weather: List<WeatherDesc>
)

data class Temp(
    val min: Double,
    val max: Double
)

data class WeatherDesc(
    val description: String,
    val icon: String
)
