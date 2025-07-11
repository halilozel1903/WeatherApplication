package com.halil.ozel.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.halil.ozel.weatherapp.model.ForecastItem
import com.halil.ozel.weatherapp.model.CurrentWeatherResponse
import com.halil.ozel.weatherapp.viewmodel.ForecastViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ForecastScreen(
    cityName: String,
    modifier: Modifier = Modifier,
    viewModel: ForecastViewModel = viewModel(),
    onBack: (() -> Unit)? = null
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(cityName) {
        viewModel.loadForecast(cityName)
    }

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = cityName, style = MaterialTheme.typography.titleLarge)
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.data != null -> ForecastContent(state.current, state.data!!.list)
                state.error != null -> Text(text = state.error ?: "Error")
            }
        }
    }
}

@Composable
private fun ForecastContent(current: CurrentWeatherResponse?, items: List<ForecastItem>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (current != null) {
            item {
                CurrentWeatherRow(current)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        items(items) { item ->
            ForecastRow(item)
        }
    }
}

@Composable
private fun ForecastRow(item: ForecastItem) {
    val icon = item.weather.firstOrNull()?.icon ?: "01d"
    val iconUrl = "https://openweathermap.org/img/wn/${'$'}icon@2x.png"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(
            text = SimpleDateFormat("dd MMM HH:mm", Locale.getDefault())
                .format(Date(item.dt * 1000)),
            modifier = Modifier.weight(1f)
        )
        AsyncImage(model = iconUrl, contentDescription = null, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${item.main.temp.toInt()}°C",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun CurrentWeatherRow(current: CurrentWeatherResponse) {
    val icon = current.weather.firstOrNull()?.icon ?: "01d"
    val iconUrl = "https://openweathermap.org/img/wn/${'$'}icon@2x.png"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(text = "Now", modifier = Modifier.weight(1f))
        AsyncImage(model = iconUrl, contentDescription = null, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${current.main.temp.toInt()}°C",
            style = MaterialTheme.typography.bodyLarge
        )    }
}
