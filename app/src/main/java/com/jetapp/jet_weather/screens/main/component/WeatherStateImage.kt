package com.jetapp.jet_weather.screens.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        modifier = Modifier.size(70.dp),
        painter = rememberAsyncImagePainter(
            model = imageUrl,
        ),
        contentDescription = "Weather Image"
    )
}