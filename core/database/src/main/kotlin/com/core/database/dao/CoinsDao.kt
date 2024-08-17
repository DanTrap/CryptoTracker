package com.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.core.database.model.CoinEntity

@Dao
interface CoinsDao {

    @Query("SELECT * FROM coins_table WHERE currency = :currency")
    suspend fun coins(currency: String): List<CoinEntity>

    @Upsert
    suspend fun upsertCoins(coins: List<CoinEntity>)
}
