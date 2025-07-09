package com.halil.ozel.weatherapp.data.remote

import com.halil.ozel.weatherapp.model.ForecastResponse
import com.halil.ozel.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    // Base URL already includes `data/2.5/`. Using a relative path here
    // would result in a duplicated segment like `data/2.5/data/2.5/onecall`
    // and lead to a 404 error. Keep only the endpoint name so Retrofit
    // correctly builds the full URL.
    @GET("onecall")
    suspend fun fetchWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "minutely,hourly,alerts",
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = ApiClient.API_KEY
    ): WeatherResponse

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
