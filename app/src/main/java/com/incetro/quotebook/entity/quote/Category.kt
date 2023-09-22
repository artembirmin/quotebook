/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.entity.quote

import android.os.Parcelable
import com.incetro.quotebook.model.data.database.category.CategoryDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Long = 0,
    val name: String
) : Parcelable {
    fun toDto(): CategoryDto = CategoryDto(id, name)
}
