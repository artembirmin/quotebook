/*
 * Quotebook
 *
 * Created by artembirmin on 10/10/2023.
 */

package com.incetro.quotebook.entity.category

data class CategoriesGptRequest(
    val messages: List<GptMessage>,
    val model: String = "gpt-3.5-turbo",
    val temperature: Float = 0.1f,
    val max_tokens: Int = 256
)
