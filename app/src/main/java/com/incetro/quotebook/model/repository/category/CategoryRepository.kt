/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.category

import com.incetro.quotebook.entity.quote.Category

interface CategoryRepository {
    suspend fun updateCategories(quoteId: Long, editedCategories: List<Category>)

}