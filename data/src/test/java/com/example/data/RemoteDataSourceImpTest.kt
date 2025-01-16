package com.example.data

import androidx.test.filters.SmallTest
import com.example.data.remote.api.ApiService
import com.example.data.remote.mapper.ForecastNetworkDataMapper
import com.example.data.remote.mapper.WeatherNetworkDataMapper
import com.example.data.remote.model.CloudsNetwork
import com.example.data.remote.model.CoordNetwork
import com.example.data.remote.model.MainNetwork
import com.example.data.remote.model.SysNetwork
import com.example.data.remote.model.WeatherNetwork
import com.example.data.remote.model.WeatherResponseNetwork
import com.example.data.remote.model.WindNetwork
import com.example.data.remote.source.RemoteDataSource
import com.example.data.remote.source.RemoteDataSourceImp
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test



@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService : ApiService
    private val weatherNetworkDataMapper = WeatherNetworkDataMapper()
    private val forecastNetworkDataMapper = ForecastNetworkDataMapper()

    private lateinit var remoteDataSource : RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService,
            weatherMapper = weatherNetworkDataMapper,
            forecastMapper = forecastNetworkDataMapper
        )
    }

    @Test
    fun test_get_Weather_success() = runBlockingTest {

        // Given
        coEvery { apiService.getWeather(any()) } returns mockData

        // When
        val result = remoteDataSource.getWeather("cairo")

        // Then
        coVerify { apiService.getWeather(any()) }

        // Assertion
        val expected = weatherNetworkDataMapper.from(mockData)
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_Weather_fail() = runBlockingTest {

        coEvery { apiService.getWeather(any()) } throws Exception()

        remoteDataSource.getWeather("cairo")

        coVerify { apiService.getWeather(any()) }
    }
}


val mockData =  WeatherResponseNetwork(
    coord = CoordNetwork(
        lat = -0.1257,
        lon = 51.5085
    ),
    weather = listOf(
        WeatherNetwork(
            id = 804,
            main = "Clouds",
            description = "overcast clouds",
            icon = "04d"
        )
    ),
    base = "stations",
    main = MainNetwork(
        temp = 285.67,
        feels_like = 284.94,
        temp_min = 284.65,
        temp_max = 286.63,
        pressure = 1017,
        humidity = 75
    ),

    visibility = 10000,
    wind = WindNetwork(
        speed = 4.63,
        deg = 300,
        gust = 0.0
    ),
    clouds = CloudsNetwork(
        all = 90
    ),
    dt = 1636814662,
    sys = SysNetwork(
        type = 2,
        id = 2019646,
        country = "GB",
        sunrise = 1636787720,
        sunset = 1636820067
    ),
    timezone= 0,
    id=2643743,
    name="London",
    cod=200
)