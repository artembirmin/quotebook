/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.database.quote

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.data.database.author.AuthorDto
import com.incetro.quotebook.model.data.database.category.CategoryDto

data class QuoteWithAuthorAndCategories(
    @Embedded val quoteDto: QuoteDto,

    @Relation(
        parentColumn = "authorId",
        entityColumn = "id"
    )
    val author: AuthorDto,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            QuoteCategoryCrossRef::class,
            parentColumn = "quoteId",
            entityColumn = "categoryId"
        )
    )
    val categories: List<CategoryDto>
) {
    fun toQuote(): Quote = Quote(
        id = quoteDto.id,
        content = quoteDto.content,
        source = quoteDto.source,
        author = author.toAuthor(),
        writingDate = quoteDto.writingDate,
        categories = categories.map { it.toCategory() },
        backgroundId = quoteDto.backgroundId
    )
}
