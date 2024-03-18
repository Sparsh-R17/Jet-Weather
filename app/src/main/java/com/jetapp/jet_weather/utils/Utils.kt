package com.jetapp.jet_weather.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(timestamp: Int): String {
    val dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT)
    val date = Date(timestamp.toLong() * 1000)

    return dateFormat.format(date)
}

fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

//fun formatDate(timestamp: Int): String {
//    val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
//    val date = Date(timestamp.toLong() * 1000)
//
//    return dateFormat.format(date)
//}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}