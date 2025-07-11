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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CityListScreen(
    modifier: Modifier = Modifier,
    onCitySelected: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val cities = listOf("Ankara", "London", "New York", "Tokyo", "Paris")
    LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
        item {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search city") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { if (query.isNotBlank()) onCitySelected(query) }) {
                Text("Search")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
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
