package com.example.data.remote.mapper

import com.example.core.Mapper
import com.example.data.data.model.ForecastDTO
import com.example.data.data.model.ForecastDayData
import com.example.data.remote.model.ForecastResponseNetwork
import javax.inject.Inject


class ForecastNetworkDataMapper @Inject constructor() :
    Mapper<ForecastResponseNetwork, ForecastDTO> {


    override fun from(i: ForecastResponseNetwork?): ForecastDTO {
        return ForecastDTO(
            code = i?.cod ?: "0",
            message = i?.message ?: "",
            listOfDays = i?.list?.map { item ->
                ForecastDayData(
                   icon = item?.weather?.firstOrNull()?.icon ?: "",
                    description = item?.weather?.firstOrNull()?.description ?: "",
                    id = item?.weather?.firstOrNull()?.id ?: 0,
                    date = item?.dt ?: 0L,
                    temp = item?.temp?.day ?: 0.0,
                    temp_max = item?.temp?.day ?: 0.0,
                    temp_min = item?.temp?.day ?: 0.0
                )
            }  ?: emptyList()
        )
    }

    override fun to(o: ForecastDTO?): ForecastResponseNetwork {
        return ForecastResponseNetwork()
    }
}