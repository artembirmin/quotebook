/*
 * Quotebook
 *
 * Created by artembirmin on 29/9/2023.
 */

package com.incetro.quotebook.model.repository.quote

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.incetro.quotebook.entity.category.Category
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Quote
import org.joda.time.DateTime

class QuoteFactory {

    private val lorem =
        LoremIpsum(50).values.toList()[0].split(" ", "\n", ".", ",").filter { it.isNotBlank() }

    fun getCategory(count: Int = 4) =
        mutableListOf<Category>().apply {
            repeat(count) {
                add(Category(name = lorem.random()))
            }
        }

    fun getAuthor() = Author(name = lorem.random())

    fun getQuote() = Quote(
        id = 0,
        content = lorem.random(),
        source = lorem.random(),
        author = getAuthor(),
        writingDate = DateTime.now(),
        categories = getCategory()
    )

}