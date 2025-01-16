package com.example.data.remote.model


data class ForecastResponseNetwork(
	val city: City? = null,
	val cnt: Int? = null,
	val cod: String? = null,
	val message: String? = null,
	val list: List<WeatherData?>? = null
)

data class City(
	val country: String? = null,
	val timezone: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val population: Int? = null
)


data class WeatherItem(
	val icon: String? = null,
	val description: String? = null,
	val main: String? = null,
	val id: Int? = null
)



data class Temp(
	val min: Double? = null,
	val max: Double? = null,
	val day: Double? = null,
	val morn: Double? = null
)


data class WeatherData(
	val sunrise: Int? = null,
	val temp: Temp? = null,
	val deg: Int? = null,
	val pressure: Int? = null,
	val clouds: Int? = null,
	val dt: Long? = null,
	val pop: Int? = null,
	val sunset: Int? = null,
	val weather: List<WeatherItem?>? = null,
	val humidity: Int? = null,
)
