package com.example.data.data.model


data class ForecastDTO(
    val code: String,
    val message: String,
    val listOfDays: List<ForecastDayData>
)

data class ForecastDayData(
    val icon: String,
    val description: String,
    val id: Int,
    val date: Long,
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
)
