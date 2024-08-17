package com.core.data.source.local

import com.core.database.dao.CoinsDao
import com.core.database.model.CoinEntity

interface CoinsLocalDataSource {

    suspend fun coins(currency: String): List<CoinEntity>

    suspend fun saveCoins(coins: List<CoinEntity>)

    class Base(private val coinsDao: CoinsDao) : CoinsLocalDataSource {

        override suspend fun coins(currency: String): List<CoinEntity> = coinsDao.coins(currency)

        override suspend fun saveCoins(coins: List<CoinEntity>) {
            coinsDao.upsertCoins(coins)
        }
    }
}
