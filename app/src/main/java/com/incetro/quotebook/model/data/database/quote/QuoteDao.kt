/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.quote

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.incetro.quotebook.model.data.database.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao : BaseDao<QuoteDto> {

    @Transaction
    @Query("SELECT * FROM ${QuoteDto.TABLE_NAME} WHERE id = :quoteId")
    suspend fun getQuoteById(quoteId: Long): QuoteWithAuthorAndCategories

    @Query("DELETE FROM ${QuoteDto.TABLE_NAME} WHERE id = :id")
    suspend fun deleteQuoteById(id: Long)

    @Transaction
    @Query("SELECT * FROM ${QuoteDto.TABLE_NAME}")
    fun observeQuotes(): Flow<List<QuoteWithAuthorAndCategories>>
}