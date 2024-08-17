package com.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.database.dao.CoinsDao
import com.core.database.model.CoinEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [CoinEntity::class]
)
abstract class Database : RoomDatabase() {

    abstract val coinsDao: CoinsDao

    companion object {
        const val APP_DATABASE = "app.db"
    }
}
