package com.halil.ozel.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halil.ozel.weatherapp.model.WeatherResponse
import com.halil.ozel.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** State for weather UI. */
data class WeatherUiState(
    val isLoading: Boolean = false,
    val data: WeatherResponse? = null,
    val error: String? = null
)

class WeatherViewModel(
    private val repository: WeatherRepository = WeatherRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState())
    val state: StateFlow<WeatherUiState> = _state

    /** Fetch weather data for the given coordinates. */
    fun loadWeather(lat: Double, lon: Double) {
        _state.value = WeatherUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = repository.getWeather(lat, lon)
                _state.value = WeatherUiState(data = response)
            } catch (e: Exception) {
                _state.value = WeatherUiState(error = e.localizedMessage)
            }
        }
    }
}
