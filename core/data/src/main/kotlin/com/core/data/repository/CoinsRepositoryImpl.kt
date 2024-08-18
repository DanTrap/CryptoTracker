package com.core.data.repository

import com.core.common.network.Resource
import com.core.data.mappers.toDomain
import com.core.data.mappers.toEntity
import com.core.data.mappers.toResponseError
import com.core.data.source.local.CoinsLocalDataSource
import com.core.data.source.remote.CoinsRemoteDataSource
import com.core.database.model.CoinEntity
import com.core.domain.model.Coin
import com.core.domain.model.CoinDetails
import com.core.domain.repository.CoinsRepository
import com.core.network.model.CoinDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class CoinsRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private val coinsLocalDataSource: CoinsLocalDataSource,
) : CoinsRepository {

    override fun coins(currency: String): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading)
        try {
            val remoteCoins = coinsRemoteDataSource.getCoins(currency)
            coinsLocalDataSource.saveCoins(
                remoteCoins.map {
                    it.toEntity(currency)
                }
            )
            emit(Resource.Success(remoteCoins.map(CoinDto::toDomain)))
        } catch (e: Throwable) {
            val cached = coinsLocalDataSource.coins(currency).map(CoinEntity::toDomain)
            if (cached.isNotEmpty()) {
                emit(Resource.FromCache(cached))
            } else {
                emit(Resource.Error(e.toResponseError()))
            }
        }
    }.flowOn(dispatcher)

    override fun getCoinDetails(id: String): Flow<Resource<CoinDetails>> = flow {
        emit(Resource.Loading)
        try {
            val remoteCoinDetails = coinsRemoteDataSource.getCoinDetails(id)
            coinsLocalDataSource.saveCoinDetails(remoteCoinDetails.toEntity())
            emit(Resource.Success(remoteCoinDetails.toDomain()))
        } catch (e: Throwable) {
            val cached = coinsLocalDataSource.getCoinDetails(id)
            if (cached != null) {
                emit(Resource.FromCache(cached.toDomain()))
            } else {
                emit(Resource.Error(e.toResponseError()))
            }
        }
    }.flowOn(dispatcher)
}
