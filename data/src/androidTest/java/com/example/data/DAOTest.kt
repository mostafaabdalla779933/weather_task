package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.local.database.AppDatabase
import com.example.data.local.database.WeatherDAO
import com.example.data.local.model.WeatherLocalModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException



@RunWith(AndroidJUnit4::class)
class TestMovieDao {
    private lateinit var weatherDAO: WeatherDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).allowMainThreadQueries().build()
        weatherDAO = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun insert_Weather_And_Read_It_From_The_List() = runBlocking {
        weatherDAO.addWeatherItem(weather)
        assert(weatherDAO.getWeatherItems().contains(weather))
    }

    @Test
    fun test_Conflict_Add_Two_Item_With_The_Same_Id(): Unit = runBlocking{


        weatherDAO.addWeatherItems(weatherList)
        weatherDAO.getWeatherItems().let{
            assert(!it.contains(weatherList[0]))
            assert(it.contains(weatherList[1]))
        }
    }
}

val weather =  WeatherLocalModel(name = "cairo", id = 1,desc = "desc", temp = 10.0, icon = "icon", humidity = 0,dt = 0,feels_like = 0.0, sunset = 0L, sunrise = 0L, pressure = 0, temp_max = 0.0, temp_min = 0.0, wind_spead = 0.0)

val weatherList = listOf(weather, weather.copy(id = 1, name = "london"))