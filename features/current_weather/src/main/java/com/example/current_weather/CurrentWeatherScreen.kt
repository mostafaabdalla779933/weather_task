package com.example.current_weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun CurrentWeatherScreen(navHostController: NavHostController, city:String, viewModel: CurrentWeatherVM = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.fetchWeather(city)
    }


}