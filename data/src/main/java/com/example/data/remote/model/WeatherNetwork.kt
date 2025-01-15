package com.example.data.remote.model

data class WeatherNetwork(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)