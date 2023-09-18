/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.utils.ext

fun <R> Boolean.ifTrue(block: (Boolean) -> R): Boolean {
    if (this) run(block)
    return this
}

fun <R> Boolean.ifFalse(block: (Boolean) -> R): Boolean {
    if (!this) run(block)
    return this
}

fun String.removeWhitespaces() = filter { !it.isWhitespace() }