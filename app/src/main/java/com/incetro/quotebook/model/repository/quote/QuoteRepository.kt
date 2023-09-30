/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.quote

import com.incetro.quotebook.entity.quote.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun createNewQuote(): Quote

    suspend fun addQuote(quote: Quote): Long

    suspend fun getQuote(quoteId: Long): Quote

    fun observeQuotes(): Flow<List<Quote>>

    suspend fun updateQuote(quote: Quote): Quote

    suspend fun deleteQuote(quote: Quote)
}