package com.example.data.remote.source

import com.example.data.data.model.ForecastDTO
import com.example.data.model.WeatherDTO

/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {

    suspend fun getWeather(city:String) : WeatherDTO

    suspend fun getForecast(city:String) : ForecastDTO
}