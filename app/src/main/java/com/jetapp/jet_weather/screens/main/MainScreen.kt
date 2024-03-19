package com.jetapp.jet_weather.screens.main

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jetapp.jet_weather.data.DataOrException
import com.jetapp.jet_weather.model.Weather
import com.jetapp.jet_weather.screens.main.component.SunDetailRow
import com.jetapp.jet_weather.screens.main.component.WeatherDailyDetailRow
import com.jetapp.jet_weather.screens.main.component.WeatherDetailRow
import com.jetapp.jet_weather.screens.main.component.WeatherStateImage
import com.jetapp.jet_weather.utils.formatDate
import com.jetapp.jet_weather.utils.formatDecimals
import com.jetapp.jet_weather.widgets.WeatherAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = "Ghaziabad")
    }.value

    if(weatherData.loading == true){
        CircularProgressIndicator()
    }else if(weatherData.data != null){
        MainScaffold(
            weather = weatherData.data!!,
            navController = navController
        )
    }
    
}

@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = weather.city.name + ", ${weather.city.country}",
                navController = navController,
                elevation = 2.dp,
            ){
                Log.d("TAG", "MainScaffold: ButtonCLicked")
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MainContent(data  = weather)
        }
    }
}



@Composable
fun MainContent(data: Weather) {
    val weatherItem = data.list.first()
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather.first().icon}.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            )
        Surface(
            modifier = Modifier
                .padding(6.dp)
                .size(180.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "°",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weatherItem.weather.first().main,
                    style = MaterialTheme.typography.titleMedium,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        WeatherDetailRow(weather = weatherItem)
        Divider(
            modifier = Modifier
                .padding(
                    horizontal = 3.dp,
                    vertical = 4.dp
                )
        )
        SunDetailRow(weather = weatherItem)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = "This Week",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
            border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.2f)),
            shape = RoundedCornerShape(14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(4.dp),
                contentPadding = PaddingValues(2.dp),
            ) {
                items(items = data.list){
                    WeatherDailyDetailRow(weather = it)
                }
            }
        }
    }
}

