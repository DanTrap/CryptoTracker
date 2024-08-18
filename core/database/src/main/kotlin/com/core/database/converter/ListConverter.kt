package com.core.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal object ListConverter {

    @TypeConverter
    internal fun stringListToString(list: List<String>): String = Json.encodeToString(list)

    @TypeConverter
    internal fun stringToStringList(string: String): List<String> = Json.decodeFromString(string)
}
