package com.example.data.data.repository

import com.example.core.Mapper
import com.example.core.Resource
import com.example.data.model.WeatherDTO
import com.example.data.remote.source.RemoteDataSource
import com.example.domain.entity.WeatherEntity
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val weatherMapper : Mapper<WeatherDTO, WeatherEntity>,
) : Repository {


    override suspend fun getWeather(city:String): Flow<Resource<WeatherEntity>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getWeather(city)
                // Save to local or update if exist
                val localId = localDataSource.getItem(data.name).id
                if (localId==-1)
                    localDataSource.addItem(data)
                else
                    localDataSource.updateItem(data)
                // Emit data
                emit(Resource.Success(weatherMapper.from(data)))
            } catch (ex : Exception) {
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getItem(name = city)
                    // Emit data
                    emit(Resource.Success(weatherMapper.from(local)))
                } catch (ex1 : Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}