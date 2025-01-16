package com.example.data.remote.api


import com.example.data.BuildConfig
import com.example.data.remote.model.ForecastResponseNetwork
import com.example.data.remote.model.WeatherResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET("weather")
    suspend fun getWeather(
        @Query("q") city : String,
        @Query("appid") apiKey : String  = BuildConfig.API_KEY,
        @Query("units") units : String  = "metric",
    ) : WeatherResponseNetwork


    @GET("forecast/daily")
    suspend fun getForecast(
        @Query("q") city : String,
        @Query("appid") apiKey : String  = BuildConfig.API_KEY,
        @Query("units") units : String  = "metric",
    ) : ForecastResponseNetwork

}