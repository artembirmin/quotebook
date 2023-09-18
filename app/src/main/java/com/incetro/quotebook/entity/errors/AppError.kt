/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.entity.errors

data class AppError(
    val error: Throwable,
    val payload: Any? = null
)