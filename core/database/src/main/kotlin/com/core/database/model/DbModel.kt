package com.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_model")
data class DbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
