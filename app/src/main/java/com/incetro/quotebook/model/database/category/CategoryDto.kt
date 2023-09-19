/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.database.category

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.quotebook.entity.quote.Category

@Entity(
    tableName = CategoryDto.TABLE_NAME
)
data class CategoryDto(
    @PrimaryKey
    val id: String,
    val name: String
) {
    fun toCategory(): Category = Category(id, name)

    companion object {
        const val TABLE_NAME = "category"
    }
}