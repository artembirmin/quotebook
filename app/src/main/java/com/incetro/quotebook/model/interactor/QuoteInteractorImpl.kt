/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.model.interactor

import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.repository.author.AuthorRepository
import com.incetro.quotebook.model.repository.category.CategoryRepository
import com.incetro.quotebook.model.repository.quote.QuoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteInteractorImpl @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val authorRepository: AuthorRepository,
    private val categoryRepository: CategoryRepository
) : QuoteInteractor {

    override suspend fun createEmptyQuote(): Quote {
        return quoteRepository.createNewQuote()
    }

    override suspend fun updateQuote(quote: Quote): Quote {
        val author = authorRepository.updateAuthor(author = quote.author)
        categoryRepository.updateCategories(quoteId = quote.id, editedCategories = quote.categories)
        return quoteRepository.updateQuote(quote.copy(author = author))
    }

    override suspend fun getQuote(quoteId: Long): Quote {
        return quoteRepository.getQuote(quoteId)
    }

    override suspend fun deleteQuote(quote: Quote) {
        quoteRepository.deleteQuote(quote)
    }
}
