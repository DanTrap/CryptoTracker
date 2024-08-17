package com.core.database.model

import androidx.room.Entity

@Entity(tableName = "coins_table", primaryKeys = ["id", "currency"])
data class CoinEntity(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val currency: String,
)
