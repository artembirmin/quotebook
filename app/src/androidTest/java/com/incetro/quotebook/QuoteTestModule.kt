/*
 * Quotebook
 *
 * Created by artembirmin on 20/9/2023.
 */

package com.incetro.quotebook

import android.content.Context
import androidx.room.Room
import com.incetro.quotebook.model.data.database.AppDatabase
import com.incetro.quotebook.model.data.database.author.AuthorDao
import com.incetro.quotebook.model.data.database.category.CategoryDao
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class QuoteTestModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideInMemoryDb(context: Context): AppDatabase =
        Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

    @Provides
    @Reusable
    fun provideQuoteDao(database: AppDatabase): QuoteDao = database.quoteDao()

    @Provides
    @Reusable
    fun provideCategoryDao(database: AppDatabase): CategoryDao = database.categoryDao()

    @Provides
    @Reusable
    fun provideAuthorDao(database: AppDatabase): AuthorDao = database.authorDao()

}