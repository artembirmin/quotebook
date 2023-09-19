/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.quote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.incetro.quotebook.model.data.database.category.CategoryDto

@Entity(
    primaryKeys = ["quoteId", "categoryId"],
    foreignKeys = [
        ForeignKey(
            entity = QuoteDto::class,
            parentColumns = ["id"],
            childColumns = ["quoteId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryDto::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class QuoteCategoryCrossRef(
    @ColumnInfo(index = true)
    val quoteId: String,
    @ColumnInfo(index = true)
    val categoryId: String
)