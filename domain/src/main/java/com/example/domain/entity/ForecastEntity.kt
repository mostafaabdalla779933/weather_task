package com.example.domain.entity



data class ForecastEntity(
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