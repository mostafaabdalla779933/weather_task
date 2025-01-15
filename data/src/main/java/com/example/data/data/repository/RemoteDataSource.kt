package com.example.data.data.repository

import com.example.data.model.WeatherDTO

/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {

    suspend fun getWeather(city:String) : WeatherDTO

}