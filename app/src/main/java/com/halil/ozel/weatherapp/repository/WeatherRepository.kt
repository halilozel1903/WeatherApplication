package com.halil.ozel.weatherapp.repository

import com.halil.ozel.weatherapp.data.remote.ApiClient
import com.halil.ozel.weatherapp.model.ForecastResponse

class WeatherRepository {
    private val service = ApiClient.weatherService

    suspend fun getForecast(cityName: String): ForecastResponse {
        return service.getWeatherForecast(cityName)
    }
}
