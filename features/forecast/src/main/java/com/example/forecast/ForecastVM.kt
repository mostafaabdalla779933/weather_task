package com.example.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.domain.usecase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForecastVM @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    val intent = MutableSharedFlow<ForecastIntent>()
    val forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)

    init {
        viewModelScope.launch {
            intent.collect { forecastIntent ->
                when (forecastIntent) {
                    is ForecastIntent.FetchForecast -> {
                        fetchForecast(forecastIntent.city)
                    }
                }
            }
        }
    }

    private fun fetchForecast(city: String) {
        viewModelScope.launch {
            getForecastUseCase.execute(city)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            forecastState.value = ForecastState.Loading
                        }
                        is Resource.Success -> {
                           forecastState.value = ForecastState.Success(it.data.listOfDays)
                        }
                        is Resource.Error -> {
                            forecastState.value = ForecastState.Error(it.exception.localizedMessage ?: "Something went wrong")
                        }
                    }
                }
        }
    }


}