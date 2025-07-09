package com.halil.ozel.weatherapp.repository

import com.halil.ozel.weatherapp.data.remote.ApiClient
import com.halil.ozel.weatherapp.model.ForecastResponse
import com.halil.ozel.weatherapp.model.WeatherResponse

class WeatherRepository {
    private val service = ApiClient.weatherService

    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return service.fetchWeather(lat, lon)
    }

    suspend fun getForecast(cityName: String): ForecastResponse {
        return service.getWeatherForecast(cityName)
    }
}
