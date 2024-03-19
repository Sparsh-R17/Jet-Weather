package com.jetapp.jet_weather.screens.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jetapp.jet_weather.R
import com.jetapp.jet_weather.model.WeatherItem
import com.jetapp.jet_weather.utils.formatDateTime

@Composable
fun SunDetailRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sun Rise Time"
            )
            Text(
                text = " ${formatDateTime(weather.sunrise)}",
                style = MaterialTheme.typography.bodyLarge
                    .copy(fontWeight = FontWeight.SemiBold)
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Text(
                text = "${formatDateTime(weather.sunset)} ",
                style = MaterialTheme.typography.bodyLarge
                    .copy(fontWeight = FontWeight.SemiBold)
            )

            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sun Set Time"
            )
        }
    }
}