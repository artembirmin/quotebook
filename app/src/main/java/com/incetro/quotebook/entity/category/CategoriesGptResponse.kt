/*
 * Quotebook
 *
 * Created by artembirmin on 10/10/2023.
 */

package com.incetro.quotebook.entity.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesGptResponse(val choices: List<GptChoice>) : Parcelable

@Parcelize
data class GptChoice(val index: Int, val message: GptMessage) : Parcelable

@Parcelize
data class GptMessage(val role: String, val content: String) : Parcelable
