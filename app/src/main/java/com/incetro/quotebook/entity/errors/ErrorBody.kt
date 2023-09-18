/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.entity.errors

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    val errors: String?,
    val code: Int,
    @SerializedName("extended_desc")
    val description: String?,
    val status: Boolean
)