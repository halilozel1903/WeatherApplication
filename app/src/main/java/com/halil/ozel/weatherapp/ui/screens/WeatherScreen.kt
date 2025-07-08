package com.halil.ozel.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.halil.ozel.weatherapp.model.Daily
import com.halil.ozel.weatherapp.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherScreen(modifier: Modifier = Modifier, viewModel: WeatherViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather(39.925533, 32.866287)
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.data != null -> WeatherContent(state.data!!)
            state.error != null -> Text(text = state.error ?: "Error")
        }
    }
}

@Composable
fun WeatherContent(response: com.halil.ozel.weatherapp.model.WeatherResponse) {
    val icon = response.current.weather.firstOrNull()?.icon ?: "01d"
    val iconUrl = "https://openweathermap.org/img/wn/${'$'}icon@2x.png"

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = iconUrl, contentDescription = null, modifier = Modifier.size(64.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${'$'}{response.current.temp.toInt()}°C", style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(response.daily) { day ->
                DailyItem(day)
            }
        }
    }
}

@Composable
fun DailyItem(day: Daily) {
    val icon = day.weather.firstOrNull()?.icon ?: "01d"
    val iconUrl = "https://openweathermap.org/img/wn/${'$'}icon@2x.png"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Text(text = SimpleDateFormat("EEE", Locale.getDefault()).format(Date(day.dt * 1000)))
        AsyncImage(model = iconUrl, contentDescription = null, modifier = Modifier.size(40.dp))
        Text(text = "${'$'}{day.temp.max.toInt()}°/${'$'}{day.temp.min.toInt()}°")
    }
}
