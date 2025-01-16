package com.example.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.core.Resource
import com.example.domain.entity.WeatherEntity
import com.example.domain.repository.Repository
import com.example.domain.usecase.GetWeatherUseCase
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class GetWeatherUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var repository: Repository

    private lateinit var getWeatherUseCase: GetWeatherUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getWeatherUseCase = GetWeatherUseCase(
            repository = repository,
            dispatcher = mainCoroutineRule.dispatcher
        )
    }

    @Test
    fun test_get_weather_success() = runBlockingTest {


        val weatherFlow = flowOf(Resource.Success(weatherMock))

        // Given
        coEvery { repository.getWeather("cairo") } returns weatherFlow

        // When & Assertions
        val result = getWeatherUseCase.execute("cairo")
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(weatherMock)
            expectComplete()
        }

        // Then
        coVerify { repository.getWeather("cairo") }

    }


    @Test
    fun test_get_weather_fail() = runBlockingTest {

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getWeather("cairo") } returns errorFlow

        // When & Assertions
        val result = getWeatherUseCase.execute("cairo")
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getWeather("cairo") }

    }


    @Test
    fun test_get_weather_fail_pass_paameter_with_null() = runBlockingTest {

        val errorFlow = flowOf(Resource.Error(Exception()))

        // When & Assertions
        val result = getWeatherUseCase.execute(null)
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then

//        coVerify { repository.getWeather() }

    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}

val weatherMock = WeatherEntity(
    1,
    "cairo",
    "desc 1",
    12.0005,
    "04d",
    12.0,
    9.0,
    12.5,
    1021,
    52,
    0.0,
    1640148442,
    1640148442,
    1640183691
)
