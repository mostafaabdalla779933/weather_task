package com.example.core

sealed class Screens(val screenRoute: String) {

    data object SearchCities : Screens(screenRoute = "SearchCities")

    data object CurrentWeather : Screens(screenRoute = "CurrentWeather/{city}") {
        fun createRoute(city: String) = "CurrentWeather/$city"
    }

    data object ForecastWeather : Screens(screenRoute = "ForecastWeather/{city}") {
        fun createRoute(city: String) = "ForecastWeather/$city"
    }
}