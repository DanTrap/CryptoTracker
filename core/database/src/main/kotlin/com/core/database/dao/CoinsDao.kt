package com.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.core.database.model.CoinDetailsEntity
import com.core.database.model.CoinEntity

@Dao
interface CoinsDao {

    @Query("SELECT * FROM coins_table WHERE currency = :currency")
    suspend fun coins(currency: String): List<CoinEntity>

    @Upsert
    suspend fun upsertCoins(coins: List<CoinEntity>)

    @Query("SELECT * FROM coin_details_table WHERE id = :id")
    suspend fun getCoinDetails(id: String): CoinDetailsEntity?

    @Upsert
    suspend fun upsertCoinDetails(coinDetails: CoinDetailsEntity)
}
