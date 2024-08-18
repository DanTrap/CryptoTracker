package com.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_details_table")
data class CoinDetailsEntity(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String,
    val categories: List<String>,
    val description: String,
    val image: String,
)
