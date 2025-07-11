package com.halil.ozel.weatherapp.model

/** Response model for current weather API */
data class CurrentWeatherResponse(
    val main: ForecastMain,
    val weather: List<WeatherDesc>
)
