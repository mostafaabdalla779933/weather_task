package com.example.weathertask.di


import com.example.core.Mapper
import com.example.data.data.mapper.WeatherDataDomainMapper
import com.example.data.local.mapper.WeatherLocalDataMapper
import com.example.data.local.model.WeatherLocalModel
import com.example.data.model.WeatherDTO
import com.example.data.remote.mapper.WeatherNetworkDataMapper
import com.example.data.remote.model.WeatherResponseNetwork
import com.example.domain.entity.WeatherEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {


    @Binds
    abstract fun bindsWeatherLocalDataMapper(mapper : WeatherLocalDataMapper) : Mapper<WeatherLocalModel, WeatherDTO>


    @Binds
    abstract fun bindsWeatherDataDomainMapper(mapper : WeatherDataDomainMapper) : Mapper<WeatherDTO, WeatherEntity>


//    @Binds
//    abstract fun bindsWeatherDomainUiMapper(mapper : WeatherDomainUiMapper) : Mapper<WeatherEntity, WeatherUiModel>

    @Binds
    abstract fun bindsWeatherNetworkDataMapper(mapper: WeatherNetworkDataMapper): Mapper<WeatherResponseNetwork, WeatherDTO>

}