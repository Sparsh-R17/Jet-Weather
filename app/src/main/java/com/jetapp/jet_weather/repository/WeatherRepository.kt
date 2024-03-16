package com.jetapp.jet_weather.repository

import com.jetapp.jet_weather.data.DataOrException
import com.jetapp.jet_weather.model.Weather
import com.jetapp.jet_weather.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {  //Using this inject we get the access to weatherapi functions
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(exception = e)
        }
        return DataOrException(data = response)
    }
}