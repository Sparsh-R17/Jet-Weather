package com.jetapp.jet_weather.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jetapp.jet_weather.R
import com.jetapp.jet_weather.data.DataOrException
import com.jetapp.jet_weather.model.Weather
import com.jetapp.jet_weather.model.WeatherItem
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
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            )
        Surface(
            modifier = Modifier
                .padding(6.dp)
                .size(200.dp),
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
        Divider(modifier = Modifier.padding(horizontal = 3.dp, vertical = 4.dp))
    }
}

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (modifier = Modifier.padding(4.dp)){
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity"
            )
            Text(
                text =  "${weather.humidity}%",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        Row (modifier = Modifier.padding(4.dp)){
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure"
            )
            Text(
                text =  " ${weather.pressure} psi",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        Row (modifier = Modifier.padding(4.dp)){
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Speed"
            )
            Text(
                text =  " ${weather.speed} mph",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

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
