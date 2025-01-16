package com.example.data.data.mapper

import com.example.core.Mapper
import com.example.data.data.model.ForecastDTO
import com.example.domain.entity.ForecastDayData
import com.example.domain.entity.ForecastEntity
import javax.inject.Inject

class ForecastDataDomainMapper @Inject constructor() :
    Mapper<ForecastDTO, ForecastEntity> {

    override fun from(i: ForecastDTO?): ForecastEntity {
        return ForecastEntity(
            code = i?.code ?: "0",
            message = i?.message ?: "",
            listOfDays = i?.listOfDays?.map {
                ForecastDayData(
                    icon = it.icon,
                    description = it.description,
                    id = it.id,
                    date = it.date,
                    temp = it.temp,
                    temp_max = it.temp_max,
                    temp_min = it.temp_min
                )
            } ?: emptyList()
        )
    }


    override fun to(o: ForecastEntity?): ForecastDTO {
        return ForecastDTO(
            code = o?.code ?: "0",
            message = o?.message ?: "",
            listOfDays = o?.listOfDays?.map {
                com.example.data.data.model.ForecastDayData(
                    icon = it.icon,
                    description = it.description,
                    id = it.id,
                    date = it.date,
                    temp = it.temp,
                    temp_max = it.temp_max,
                    temp_min = it.temp_min
                )
            } ?: emptyList()
        )
    }
}