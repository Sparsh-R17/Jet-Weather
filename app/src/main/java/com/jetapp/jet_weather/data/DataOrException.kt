package com.jetapp.jet_weather.data

//Wrapper class to add more info about the status of request
class DataOrException<T, Boolean, E: Exception> (
    var data : T? = null,
    var loading: Boolean? = null,
    var exception: E? = null,
)