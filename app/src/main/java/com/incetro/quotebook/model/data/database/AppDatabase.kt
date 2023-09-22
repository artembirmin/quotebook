/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.incetro.quotebook.BuildConfig
import com.incetro.quotebook.model.data.database.author.AuthorDao
import com.incetro.quotebook.model.data.database.author.AuthorDto
import com.incetro.quotebook.model.data.database.category.CategoryDao
import com.incetro.quotebook.model.data.database.category.CategoryDto
import com.incetro.quotebook.model.data.database.quote.QuoteCategoryCrossRef
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import com.incetro.quotebook.model.data.database.quote.QuoteDto

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
        const val VERSION = 3
    }

    abstract fun quoteDao(): QuoteDao
    abstract fun categoryDao(): CategoryDao
    abstract fun authorDao(): AuthorDao
}