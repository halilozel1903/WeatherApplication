package com.halil.ozel.weatherapp.data.remote

import com.halil.ozel.weatherapp.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    /**
     * Fetch 5 day / 3 hour forecast data by city name.
     * Using relative path here since BASE_URL already ends with `data/2.5/`.
     */
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = ApiClient.API_KEY
    ): ForecastResponse
}
