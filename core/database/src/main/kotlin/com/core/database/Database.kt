package com.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.database.converter.ListConverter
import com.core.database.dao.CoinsDao
import com.core.database.model.CoinDetailsEntity
import com.core.database.model.CoinEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [CoinEntity::class, CoinDetailsEntity::class],
)
@TypeConverters(ListConverter::class)
abstract class Database : RoomDatabase() {

    abstract val coinsDao: CoinsDao

    companion object {
        const val APP_DATABASE = "app.db"
    }
}
