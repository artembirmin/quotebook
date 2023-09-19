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
import com.incetro.quotebook.model.database.author.AuthorDto
import com.incetro.quotebook.model.database.category.CategoryDto
import com.incetro.quotebook.model.database.quote.QuoteCategoryCrossRef
import com.incetro.quotebook.model.database.quote.QuoteDao
import com.incetro.quotebook.model.database.quote.QuoteDto

@Database(
    entities = [
        QuoteDto::class,
        AuthorDto::class,
        CategoryDto::class,
        QuoteCategoryCrossRef::class,
    ],
    version = AppDatabase.VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = BuildConfig.DB_NAME
        const val VERSION = 31
    }

    abstract fun demoDao(): QuoteDao
}