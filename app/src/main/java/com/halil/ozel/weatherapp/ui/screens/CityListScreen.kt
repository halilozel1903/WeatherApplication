package com.halil.ozel.weatherapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CityListScreen(
    modifier: Modifier = Modifier,
    onCitySelected: (String) -> Unit
) {
    val cities = listOf("Ankara", "London", "New York", "Tokyo", "Paris")
    LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
        items(cities) { city ->
            CityRow(city = city, onClick = { onCitySelected(city) })
        }
    }
}

@Composable
private fun CityRow(city: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Icon(imageVector = Icons.Default.Public, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = city, style = MaterialTheme.typography.bodyLarge)
    }
}
