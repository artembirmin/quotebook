/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.quote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(
    tableName = QuoteDto.TABLE_NAME
)
data class QuoteDto(
    @PrimaryKey
    val id: String,
    val content: String,
    val source: String,
    val authorId: String,
    val writingDate: DateTime,
) {
    companion object {
        const val TABLE_NAME = "quote"
    }
}
