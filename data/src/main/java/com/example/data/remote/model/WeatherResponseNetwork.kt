package com.example.data.remote.model


data class WeatherResponseNetwork(
    val base: String = "",
    val clouds: CloudsNetwork? = null,
    val cod: Int = 0,
    val coord: CoordNetwork? = null,
    val dt: Long = 0,
    val id: Int = 0,
    val main: MainNetwork? = null,
    val name: String = "",
    val sys: SysNetwork? = null,
    val timezone: Int = 0,
    val visibility: Int = 0,
    val weather: List<WeatherNetwork>? = null,
    val wind: WindNetwork? = null
)


data class CloudsNetwork(
	val all: Int
)

data class CoordNetwork(
	val lat: Double,
	val lon: Double
)

data class MainNetwork(
	val feels_like: Double,
	val humidity: Int,
	val pressure: Int,
	val temp: Double,
	val temp_max: Double,
	val temp_min: Double
)

data class WindNetwork(
	val deg: Int,
	val gust: Double,
	val speed: Double
)

data class WeatherNetwork(
	val id: Int,
	val main: String,
	val description: String,
	val icon: String
)


data class SysNetwork(
	val country: String,
	val id: Int,
	val sunrise: Long,
	val sunset: Long,
	val type: Int
)