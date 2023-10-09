/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.model.interactor

import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.repository.author.AuthorRepository
import com.incetro.quotebook.model.repository.category.CategoryRepository
import com.incetro.quotebook.model.repository.quote.QuoteRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteInteractorImpl @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val authorRepository: AuthorRepository,
    private val categoryRepository: CategoryRepository
) : QuoteInteractor {

    override suspend fun createEmptyQuote(): Quote {
        val emptyAuthor = authorRepository.updateOrCreateAuthor(author = Author())
        val newQuote = quoteRepository.createNewQuote(emptyAuthorId = emptyAuthor.id)
        return quoteRepository.updateQuote(newQuote.copy(author = emptyAuthor))
    }

    override suspend fun observeQuotes(): Flow<List<Quote>> {
        return quoteRepository.observeQuotes()
    }

    override suspend fun addQuotes(quotes: List<Quote>) {
        quotes.forEach { quote ->
            Timber.e("addQuotes quote = $quote")
            addQuote(quote)
        }
    }

    override suspend fun addQuote(newQuote: Quote) {
        val author = authorRepository.updateOrCreateAuthor(author = newQuote.author)
        val quoteId = quoteRepository.addQuote(newQuote.copy(author = author))
        categoryRepository.updateCategories(
            quoteId = quoteId,
            editedCategories = newQuote.categories
        )
    }

    override suspend fun updateQuote(quote: Quote): Quote {
        Timber.e("updateQuote quote = $quote")
        val author = authorRepository.updateOrCreateAuthor(author = quote.author)
        categoryRepository.updateCategories(quoteId = quote.id, editedCategories = quote.categories)
        return quoteRepository.updateQuote(quote.copy(author = author))
    }

    override suspend fun getQuote(quoteId: Long): Quote {
        return quoteRepository.getQuote(quoteId)
    }

    override suspend fun deleteQuote(quote: Quote) {
        quoteRepository.deleteQuote(quote)
    }

    override suspend fun fetchCategories(quote: Quote): List<Category> {
        return categoryRepository.fetchCategories(quote)
    }
}
