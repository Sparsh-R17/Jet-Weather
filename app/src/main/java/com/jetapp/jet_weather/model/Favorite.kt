package com.jetapp.jet_weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "city") //not necessary to keep the column name it will take the value of variable if not specified
    val city: String,
    @ColumnInfo(name = "country")
    val country: String,
)
