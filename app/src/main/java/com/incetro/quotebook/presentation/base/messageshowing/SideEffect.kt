/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.base.messageshowing

import androidx.annotation.DrawableRes

sealed interface SideEffect {

    data class ToastMessageState(
        val text: String = "",
        @DrawableRes val icon: Int? = null
    ) : SideEffect

    object None : SideEffect
}