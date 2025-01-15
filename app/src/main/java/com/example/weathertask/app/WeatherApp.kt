package com.example.weathertask.app

import android.app.Application
import com.example.weathertask.R
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext, getString(R.string.google_maps_key))
    }
}