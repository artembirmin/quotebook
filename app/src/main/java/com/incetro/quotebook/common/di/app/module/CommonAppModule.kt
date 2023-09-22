/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.common.di.app.module

import com.incetro.quotebook.model.interactor.QuoteInteractor
import com.incetro.quotebook.model.interactor.QuoteInteractorImpl
import com.incetro.quotebook.model.repository.author.AuthorRepository
import com.incetro.quotebook.model.repository.author.AuthorRepositoryImpl
import com.incetro.quotebook.model.repository.category.CategoryRepository
import com.incetro.quotebook.model.repository.category.CategoryRepositoryImpl
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

    @Binds
    @Singleton
    abstract fun provideAuthorRepository(authorRepositoryImpl: AuthorRepositoryImpl): AuthorRepository

    @Binds
    @Singleton
    abstract fun provideCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    abstract fun provideEditInteractor(quoteInteractorImpl: QuoteInteractorImpl): QuoteInteractor
    //endregion

    //region UseCase

    //endregion

}