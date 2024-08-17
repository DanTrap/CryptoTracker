package com.core.data.repository

import com.core.common.network.Resource
import com.core.common.network.ResponseError
import com.core.data.mappers.toDomain
import com.core.data.mappers.toEntity
import com.core.data.source.local.CoinsLocalDataSource
import com.core.data.source.remote.CoinsRemoteDataSource
import com.core.database.model.CoinEntity
import com.core.domain.model.Coin
import com.core.domain.repository.CoinsRepository
import com.core.network.model.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class CoinsRepositoryImpl(
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private val coinsLocalDataSource: CoinsLocalDataSource,
) : CoinsRepository {

    override fun coins(currency: String): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading)
        try {
            val remoteCoins = coinsRemoteDataSource.getCoins(currency)
            coinsLocalDataSource.saveCoins(remoteCoins.map {
                it.toEntity(currency)
            })
            emit(Resource.Success(remoteCoins.map(CoinDto::toDomain)))
        } catch (e: Exception) {
            e.printStackTrace()
            val cached = coinsLocalDataSource.coins(currency).map(CoinEntity::toDomain)
            if (cached.isNotEmpty()) {
                emit(Resource.FromCache(cached))
            } else {
                //todo
                emit(Resource.Error(ResponseError.SSL))
            }
        }
    }.flowOn(Dispatchers.IO)
}
