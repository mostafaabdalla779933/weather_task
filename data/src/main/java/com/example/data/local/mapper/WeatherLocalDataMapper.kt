package com.example.data.local.mapper


import com.example.core.Mapper
import com.example.data.local.model.WeatherLocalModel
import com.example.data.model.WeatherDTO
import javax.inject.Inject

class WeatherLocalDataMapper @Inject constructor() : Mapper<WeatherLocalModel, WeatherDTO> {

    override fun from(i: WeatherLocalModel?): WeatherDTO {
        return WeatherDTO(
            id = i?.id ?: 0,
            name = i?.name ?: "",
            desc = i?.desc ?: "",
            temp = i?.temp ?: 0.0,
            icon = i?.icon ?: "",
            feels_like = i?.feels_like ?: 0.0,
            temp_min = i?.temp_min ?: 0.0,
            temp_max = i?.temp_max ?: 0.0,
            pressure = i?.pressure ?: 0,
            humidity = i?.humidity ?: 0,
            wind_spead = i?.wind_spead ?: 0.0,
            sunrise = i?.sunrise ?: 0,
            sunset = i?.sunset ?: 0,
            dt = i?.dt ?: 0,
        )
    }

    override fun to(o: WeatherDTO?): WeatherLocalModel {
        return WeatherLocalModel(
            id = o?.id?:0,
            name = o?.name?:"",
            desc = o?.desc?:"",
            temp = o?.temp?:0.0,
            icon = o?.icon?:"",
            feels_like = o?.feels_like?:0.0,
            temp_min = o?.temp_min?:0.0,
            temp_max = o?.temp_max?:0.0,
            pressure = o?.pressure?:0,
            humidity = o?.humidity?:0,
            wind_spead = o?.wind_spead?:0.0,
            sunrise = o?.sunrise?:0,
            sunset = o?.sunset?:0,
            dt = o?.dt?:0,
        )
    }
}