package com.jetapp.jet_weather.screens.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jetapp.jet_weather.model.WeatherItem
import com.jetapp.jet_weather.utils.formatDate
import com.jetapp.jet_weather.utils.formatDecimals

@Composable
fun WeatherDailyDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather.first().icon}.png"
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = formatDate(weather.dt).split(",")[0]
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier
                    .width(80.dp)
                    .padding(4.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = weather.weather.first().main
                )
            }
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(formatDecimals(weather.temp.max) + "°")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(formatDecimals(weather.temp.min) + "°")
                    }
                })
        }
    }
}