/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.common.di.app.module

import android.content.Context
import androidx.room.Room
import com.incetro.quotebook.model.data.database.AppDatabase
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DB_NAME
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideDemoDao(database: AppDatabase): QuoteDao = database.demoDao()
}