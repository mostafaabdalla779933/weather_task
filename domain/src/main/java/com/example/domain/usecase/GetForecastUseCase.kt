package com.example.domain.usecase

import com.example.core.BaseUseCase
import com.example.core.Resource
import com.example.domain.entity.ForecastEntity
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetForecastUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<ForecastEntity, String>() {

    override suspend fun buildRequest(params: String?): Flow<Resource<ForecastEntity>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(Exception("id can not be null")))
            }.flowOn(dispatcher)
        }
        return repository.getForecast(params).flowOn(dispatcher)
    }
}