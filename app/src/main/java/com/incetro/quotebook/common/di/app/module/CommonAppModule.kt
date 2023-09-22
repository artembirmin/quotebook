/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.common.di.app.module

import com.incetro.quotebook.model.repository.quote.QuoteRepository
import com.incetro.quotebook.model.repository.quote.QuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CommonAppModule {

    //region Interactors and Repositories
    @Binds
    @Singleton
    abstract fun provideQuoteRepository(quoteRepositoryImpl: QuoteRepositoryImpl): QuoteRepository
    //endregion

    //region UseCase

    //endregion

}