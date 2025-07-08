package com.halil.ozel.weatherapp.data.remote

import com.halil.ozel.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/onecall")
    suspend fun fetchWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "minutely,hourly,alerts",
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = ApiClient.API_KEY
    ): WeatherResponse
}
