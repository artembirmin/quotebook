/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.quote

import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import com.incetro.quotebook.model.data.database.quote.QuoteDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    private val quoteDao: QuoteDao,
) : QuoteRepository {

    override suspend fun createNewQuote(emptyAuthorId: Long): Quote {
        val writingDate = DateTime.now()
        val newQuoteId = quoteDao.insert(
            QuoteDto(
                authorId = emptyAuthorId,
                writingDate = writingDate,
            )
        )
        return Quote(id = newQuoteId, writingDate = writingDate)
    }

    /**
     * @return Added quote id.
     */
    override suspend fun addQuote(quote: Quote): Long {
        return quoteDao.insert(quote.toQuoteDto())
    }

    override suspend fun getQuote(quoteId: Long): Quote {
        return quoteDao.getQuoteById(quoteId).toQuote()
    }

    override fun observeQuotes(): Flow<List<Quote>> {
        return quoteDao.observeQuotes()
            .map {
                Timber.e("observeQuotes quote = $it")
                it.map { quoteWithAuthorAndCategories -> quoteWithAuthorAndCategories.toQuote() }
            }
    }

    override suspend fun updateQuote(quote: Quote): Quote {
        Timber.e("updateQuote quote = $quote")
        quoteDao.update(quote.toQuoteDto(writingDate = DateTime.now()))
        return quoteDao.getQuoteById(quote.id).also { Timber.e("updateQuote getQuoteById = $it") }
            .toQuote()
    }

    override suspend fun deleteQuote(quote: Quote) {
        quoteDao.deleteQuoteById(quote.id)
    }
}
