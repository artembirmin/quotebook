/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.incetro.quotebook.BuildConfig
import com.incetro.quotebook.model.database.demo.DemoDao
import com.incetro.quotebook.model.database.demo.DemoDto

@Database(
    entities = [
        DemoDto::class,
    ],
    version = AppDatabase.VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = BuildConfig.DB_NAME
        const val VERSION = 31
    }

    abstract fun demoDao(): DemoDao
}