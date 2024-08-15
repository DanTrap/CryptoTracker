package com.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.core.database.model.DbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DbDao {

    @Query("SELECT * FROM db_model")
    fun completedQuizzes(): Flow<List<DbModel>>
}
