package com.halil.ozel.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.halil.ozel.weatherapp.ui.screens.ForecastScreen
import com.halil.ozel.weatherapp.ui.screens.CityListScreen
import com.halil.ozel.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.CityList) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (val screen = currentScreen) {
                        Screen.CityList ->
                            CityListScreen(
                                modifier = Modifier.padding(innerPadding),
                                onCitySelected = { city -> currentScreen = Screen.Forecast(city) }
                            )
                        is Screen.Forecast ->
                            ForecastScreen(
                                cityName = screen.city,
                                modifier = Modifier.padding(innerPadding),
                                onBack = { currentScreen = Screen.CityList }
                            )
                    }
                }
            }
        }
    }
}

private sealed interface Screen {
    object CityList : Screen
    data class Forecast(val city: String) : Screen
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppTheme {
        Column {
            CityListScreen(onCitySelected = {})
        }
    }}
