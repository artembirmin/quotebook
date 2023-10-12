/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.incetro.quotebook.model.data.database.BaseDao
import com.incetro.quotebook.model.data.database.quote.QuoteCategoryCrossRef
import timber.log.Timber

@Dao
interface CategoryDao : BaseDao<CategoryDto> {

    @Transaction
    suspend fun addCategory(categoryName: String, quoteId: Long) {
        val categoryId = getCategoryByName(categoryName)?.id
            ?: insert(CategoryDto(name = categoryName))
        Timber.e("quoteId = $quoteId, categoryId = $categoryId")
        insertCrossRefTable(
            QuoteCategoryCrossRef(
                quoteId = quoteId,
                categoryId = categoryId,
            )
        )
    }

    @Query("SELECT * FROM ${CategoryDto.TABLE_NAME} WHERE name = :categoryName")
    suspend fun getCategoryByName(categoryName: String): CategoryDto?

    @Insert
    suspend fun insertCrossRefTable(crossRef: QuoteCategoryCrossRef)

}