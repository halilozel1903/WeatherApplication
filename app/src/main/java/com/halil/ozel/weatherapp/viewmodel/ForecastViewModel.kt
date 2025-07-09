package com.halil.ozel.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halil.ozel.weatherapp.model.ForecastResponse
import com.halil.ozel.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** State for forecast UI. */
data class ForecastUiState(
    val isLoading: Boolean = false,
    val data: ForecastResponse? = null,
    val error: String? = null
)

class ForecastViewModel(
    private val repository: WeatherRepository = WeatherRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(ForecastUiState())
    val state: StateFlow<ForecastUiState> = _state

    /** Fetch forecast data for the given city name. */
    fun loadForecast(cityName: String) {
        _state.value = ForecastUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = repository.getForecast(cityName)
                _state.value = ForecastUiState(data = response)
            } catch (e: Exception) {
                _state.value = ForecastUiState(error = e.localizedMessage)
            }
        }
    }
}
