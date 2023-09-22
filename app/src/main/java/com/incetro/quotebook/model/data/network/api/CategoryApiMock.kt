/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.data.network.api

import kotlinx.coroutines.delay

class CategoryApiMock {
    var requestCount = 0

    val categories = mapOf<Int, List<String>>(
        0 to listOf("category1", "category2"),
        1 to listOf("category1", "category2"),
        2 to listOf("category1", "category2", "category3"),
        3 to listOf("category1", "category2", "category4"),
        4 to listOf("category2", "category1"),
        5 to listOf("category1"),
        6 to listOf("category3"),
    )

    suspend fun getCategories(quote: String): List<String> {
        delay(500)
        return categories[requestCount].also { requestCount++ }!!
    }
}