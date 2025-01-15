package com.example.weathertask.di

import com.example.data.data.repository.LocalDataSource
import com.example.data.remote.source.RemoteDataSource
import com.example.data.remote.source.RemoteDataSourceImp
import com.example.data.local.source.LocalDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Repository classes
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImp): LocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

}