package com.example.current_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrentWeatherVM @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) :ViewModel(){


    fun fetchWeather(city: String) {
        viewModelScope.launch {
            getWeatherUseCase.execute(city)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            // Set State
                            //   setState { copy(weatherState = MainContract.WeatherState.Loading) }
                        }

                        is Resource.Empty -> {
                            // Set State
                            //  setState { copy(weatherState = MainContract.WeatherState.Idle) }
                        }

                        is Resource.Success -> {
                            // Set State
//                            val weatherList = weatherMapper.fromList(listOf(it.data))
//                            setState {
//                                copy(
//                                    weatherState = MainContract.WeatherState.Success(
//                                        weatherList = weatherList
//                                    )
//                                )
//                            }
                        }

                        is Resource.Error -> {
                            // Set Effect
//                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }


}