package com.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.database.dao.DbDao
import com.core.database.model.DbModel

@Database(
    version = 1,
    exportSchema = false,
    entities = [DbModel::class]
)
abstract class Database : RoomDatabase() {

    abstract val dbDao: DbDao

    companion object {
        const val APP_DATABASE = "app.db"
    }
}
