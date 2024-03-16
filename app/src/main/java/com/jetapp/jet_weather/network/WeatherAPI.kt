package com.jetapp.jet_weather.network

import com.jetapp.jet_weather.model.Weather
import com.jetapp.jet_weather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton //It blocks creating multiple instances of the same class
interface WeatherAPI {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("units") units : String = "metric",
        @Query("appid") appid : String = Constants.API_KEY
    ) : Weather
}