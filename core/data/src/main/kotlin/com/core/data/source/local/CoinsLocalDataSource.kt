package com.core.data.source.local

import com.core.database.dao.CoinsDao
import com.core.database.model.CoinDetailsEntity
import com.core.database.model.CoinEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CoinsLocalDataSource {

    suspend fun coins(currency: String): List<CoinEntity>

    suspend fun saveCoins(coins: List<CoinEntity>)

    suspend fun getCoinDetails(id: String): CoinDetailsEntity?

    suspend fun saveCoinDetails(coinDetails: CoinDetailsEntity)

    class Base(
        private val dispatcher: CoroutineDispatcher,
        private val coinsDao: CoinsDao,
    ) : CoinsLocalDataSource {

        override suspend fun coins(
            currency: String,
        ): List<CoinEntity> = withContext(dispatcher) {
            coinsDao.coins(currency)
        }

        override suspend fun saveCoins(
            coins: List<CoinEntity>,
        ) = withContext(dispatcher) {
            coinsDao.upsertCoins(coins)
        }

        override suspend fun getCoinDetails(
            id: String,
        ): CoinDetailsEntity? = withContext(dispatcher) {
            coinsDao.getCoinDetails(id)
        }

        override suspend fun saveCoinDetails(
            coinDetails: CoinDetailsEntity,
        ) = withContext(dispatcher) {
            coinsDao.upsertCoinDetails(coinDetails)
        }
    }
}
