/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.model.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

object Converters {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toDateTime(value: Long?) = value?.let { DateTime(it, DateTimeZone.UTC) }

    @TypeConverter
    @JvmStatic
    fun fromDateTime(value: DateTime?) = value?.millis
}