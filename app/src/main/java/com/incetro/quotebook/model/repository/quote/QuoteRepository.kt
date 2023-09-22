/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.quote

import com.incetro.quotebook.entity.quote.Quote

interface QuoteRepository {

    suspend fun createNewQuote(): Quote

    suspend fun getQuote(quoteId: Int): Quote

    suspend fun updateQuote(quote: Quote): Quote

    suspend fun deleteQuote(quote: Quote)
}