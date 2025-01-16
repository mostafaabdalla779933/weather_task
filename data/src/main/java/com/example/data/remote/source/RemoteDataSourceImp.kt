package com.example.data.remote.source

import com.example.core.Mapper
import com.example.data.data.model.ForecastDTO
import com.example.data.model.WeatherDTO
import com.example.data.remote.api.ApiService
import com.example.data.remote.model.ForecastResponseNetwork
import com.example.data.remote.model.WeatherResponseNetwork
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService : ApiService,
    private val weatherMapper : Mapper<WeatherResponseNetwork, WeatherDTO>,
    private val forecastMapper : Mapper<ForecastResponseNetwork, ForecastDTO>
    ) : RemoteDataSource {


    override suspend fun getWeather(city:String): WeatherDTO {
        val networkData = apiService.getWeather(city = city)
        return weatherMapper.from(networkData)
    }

    override suspend fun getForecast(city: String): ForecastDTO {
       val networkData = apiService.getForecast(city = city)
       return forecastMapper.from(networkData)
    }
}