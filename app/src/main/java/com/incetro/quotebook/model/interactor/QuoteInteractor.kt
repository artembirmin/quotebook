/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.model.interactor

import com.incetro.quotebook.entity.quote.Quote

interface QuoteInteractor {
    suspend fun createEmptyQuote(): Quote
    suspend fun updateQuote(quote: Quote): Quote
    suspend fun getQuote(quoteId: Long): Quote
    suspend fun deleteQuote(quote: Quote)
}