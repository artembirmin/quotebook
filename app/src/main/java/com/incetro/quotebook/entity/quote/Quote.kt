/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.entity.quote

import org.joda.time.DateTime

data class Quote(
    val content: String,
    val source: String,
    val author: Author,
    val writingDate: DateTime,
    val categories: List<Category>,
)


