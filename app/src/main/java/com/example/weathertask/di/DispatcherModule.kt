package com.example.weathertask.di

import com.example.domain.qualifiers.DefaultDispatcher
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.qualifiers.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @DefaultDispatcher
    @Provides
    internal fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    internal fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    internal fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}