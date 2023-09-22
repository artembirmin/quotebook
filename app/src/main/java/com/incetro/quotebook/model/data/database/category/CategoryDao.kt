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

@Dao
interface CategoryDao : BaseDao<CategoryDto> {

    @Transaction
    suspend fun addCategory(categoryName: String, quoteId: Long) {
        val categoryId = getCategoryByName(categoryName)?.id
            ?: insert(CategoryDto(name = categoryName))
        updateCrossRefTable(
            QuoteCategoryCrossRef(
                quoteId = quoteId.toLong(),
                categoryId = categoryId.toLong(),
            )
        )
    }

    @Query("SELECT * FROM ${CategoryDto.TABLE_NAME} WHERE name = :categoryName")
    suspend fun getCategoryByName(categoryName: String): CategoryDto?

    @Insert
    suspend fun updateCrossRefTable(crossRef: QuoteCategoryCrossRef)

}