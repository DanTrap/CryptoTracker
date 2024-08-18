package com.core.data.source.local

import com.core.database.dao.CoinsDao
import com.core.database.model.CoinDetailsEntity
import com.core.database.model.CoinEntity

interface CoinsLocalDataSource {

    suspend fun coins(currency: String): List<CoinEntity>

    suspend fun saveCoins(coins: List<CoinEntity>)

    suspend fun getCoinDetails(id: String): CoinDetailsEntity?

    suspend fun saveCoinDetails(coinDetails: CoinDetailsEntity)

    class Base(private val coinsDao: CoinsDao) : CoinsLocalDataSource {

        override suspend fun coins(currency: String): List<CoinEntity> = coinsDao.coins(currency)

        override suspend fun saveCoins(coins: List<CoinEntity>) {
            coinsDao.upsertCoins(coins)
        }

        override suspend fun getCoinDetails(id: String): CoinDetailsEntity? =
            coinsDao.getCoinDetails(id)

        override suspend fun saveCoinDetails(coinDetails: CoinDetailsEntity) {
            coinsDao.upsertCoinDetails(coinDetails)
        }
    }
}
