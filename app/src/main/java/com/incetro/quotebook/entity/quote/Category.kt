/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.entity.quote

import com.incetro.quotebook.model.data.database.category.CategoryDto

data class Category(
    val id: Long = 0,
    val name: String
) {
    fun toDto(): CategoryDto = CategoryDto(id, name)
}
