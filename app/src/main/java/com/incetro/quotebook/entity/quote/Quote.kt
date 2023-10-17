/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.entity.quote

import android.os.Parcelable
import com.incetro.quotebook.entity.category.Category
import com.incetro.quotebook.model.data.database.quote.QuoteDto
import kotlinx.parcelize.Parcelize
import org.joda.time.DateTime

@Parcelize
data class Quote(
    val id: Long = 0,
    val content: String = "",
    val source: String = "",
    val author: Author = Author(),
    val writingDate: DateTime,
    val categories: List<Category> = emptyList(),
    val backgroundId: Int? = null
) : Parcelable {

    fun toQuoteDto(writingDate: DateTime? = null): QuoteDto = QuoteDto(
        id = id,
        content = content,
        source = source,
        authorId = author.id,
        writingDate = writingDate ?: this.writingDate,
        backgroundId = backgroundId
    )
}


