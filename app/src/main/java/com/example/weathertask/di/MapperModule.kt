package com.example.weathertask.di


import com.example.core.Mapper
import com.example.data.data.mapper.ForecastDataDomainMapper
import com.example.data.data.mapper.WeatherDataDomainMapper
import com.example.data.data.model.ForecastDTO
import com.example.data.local.mapper.WeatherLocalDataMapper
import com.example.data.local.model.WeatherLocalModel
import com.example.data.model.WeatherDTO
import com.example.data.remote.mapper.ForecastNetworkDataMapper
import com.example.data.remote.mapper.WeatherNetworkDataMapper
import com.example.data.remote.model.ForecastResponseNetwork
import com.example.data.remote.model.WeatherResponseNetwork
import com.example.domain.entity.ForecastEntity
import com.example.domain.entity.WeatherEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {


    @Binds
    abstract fun bindsWeatherLocalDataMapper(mapper: WeatherLocalDataMapper): Mapper<WeatherLocalModel, WeatherDTO>


    @Binds
    abstract fun bindsWeatherDataDomainMapper(mapper: WeatherDataDomainMapper): Mapper<WeatherDTO, WeatherEntity>

    @Binds
    abstract fun bindsWeatherNetworkDataMapper(mapper: WeatherNetworkDataMapper): Mapper<WeatherResponseNetwork, WeatherDTO>


    @Binds
    abstract fun bindsForecastDataDomainMapper(mapper: ForecastDataDomainMapper): Mapper<ForecastDTO, ForecastEntity>

    @Binds
    abstract fun bindsForecastNetworkDataMapper(mapper: ForecastNetworkDataMapper): Mapper<ForecastResponseNetwork, ForecastDTO>

}