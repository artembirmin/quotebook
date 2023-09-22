/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.quote

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(
    tableName = QuoteDto.TABLE_NAME
)
data class QuoteDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val content: String = "",
    val source: String = "",
    val authorId: Long? = null,
    val writingDate: DateTime = DateTime.now(),
) {

    companion object {
        const val TABLE_NAME = "quote"
    }
}
