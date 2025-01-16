package com.example.current_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.domain.entity.WeatherEntity
import com.example.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrentWeatherVM @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) :ViewModel(){

    var loadingState = MutableStateFlow(false)
    var weatherState = MutableStateFlow<WeatherEntity?>(null)

    var error = MutableSharedFlow<String>()

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            getWeatherUseCase.execute(city)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            loadingState.value = true
                        }

                        is Resource.Success -> {
                            loadingState.value = false
                            weatherState.value = it.data

                        }

                        is Resource.Error -> {
                            loadingState.value = false
                            error.emit(it.exception.localizedMessage ?: "Something went wrong")
                        }
                    }
                }
        }
    }


}