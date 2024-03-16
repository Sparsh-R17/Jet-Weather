package com.jetapp.jet_weather.screens.main

import androidx.lifecycle.ViewModel
import com.jetapp.jet_weather.data.DataOrException
import com.jetapp.jet_weather.model.Weather
import com.jetapp.jet_weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    suspend fun getWeatherData(city : String) : DataOrException<Weather,Boolean, Exception>{
        return repository.getWeather(cityQuery = city)
    }
}