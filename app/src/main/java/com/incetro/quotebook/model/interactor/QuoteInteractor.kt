/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.model.interactor

import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.entity.quote.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteInteractor {
    suspend fun createEmptyQuote(): Quote
    suspend fun observeQuotes(): Flow<List<Quote>>
    suspend fun addQuotes(quotes: List<Quote>)
    suspend fun addQuote(newQuote: Quote)
    suspend fun updateQuote(quote: Quote): Quote
    suspend fun getQuote(quoteId: Long): Quote
    suspend fun deleteQuote(quote: Quote)
    suspend fun fetchCategories(quote: Quote): List<Category>
}