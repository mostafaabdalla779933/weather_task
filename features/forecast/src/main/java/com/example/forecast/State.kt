package com.example.forecast

import com.example.domain.entity.ForecastDayData

sealed class ForecastState {
    data object Loading : ForecastState()
    data class Success(val data: List<ForecastDayData>) : ForecastState()
    data class Error(val message: String) : ForecastState()
}

sealed class ForecastIntent {
    data class FetchForecast(val city: String) : ForecastIntent()
}