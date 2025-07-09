package com.halil.ozel.weatherapp.model

/** Response model for 5 day / 3 hour forecast API. */
data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt: Long,
    val main: ForecastMain,
    val weather: List<WeatherDesc>
)

data class ForecastMain(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double
)

data class WeatherDesc(
    val icon: String,
    val description: String
)
