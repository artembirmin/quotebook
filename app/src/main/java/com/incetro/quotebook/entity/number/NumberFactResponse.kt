/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.entity.number

data class NumberFactResponse(
    val text: String,
    val found: Boolean,
    val number: Long,
)