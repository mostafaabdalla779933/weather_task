package com.example.weathertask.di

import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlacesModule {

    @Provides
    fun providePlacesClient(@ApplicationContext context: Context): PlacesClient =
        Places.createClient(context)
}