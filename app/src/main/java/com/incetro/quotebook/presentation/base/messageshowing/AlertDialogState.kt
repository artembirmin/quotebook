/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.base.messageshowing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.text.AnnotatedString
import com.incetro.quotebook.R

/**
 * Mark this by [kotlinx.parcelize.IgnoredOnParcel].
 */
data class AlertDialogState(
    val isVisible: Boolean = false,
    val title: DialogString? = null,
    val text: DialogString? = null,
    @StringRes val positiveText: Int? = R.string.ok,
    @StringRes val negativeText: Int? = null,
    @DrawableRes val icon: Int? = null,
    val onPositiveClick: (() -> Unit)? = null,
    val onNegativeClick: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null,
    val cancelable: Boolean = true
)

sealed interface DialogString {
    data class StringText(val value: String) : DialogString
    data class StringResText(@StringRes val value: Int) : DialogString
    data class AnnotatedStringText(val value: AnnotatedString) : DialogString
}

//sealed interface AlertDialogState {
//    val isVisible: Boolean
//
//    /**
//     * If you want to use SpannableString, you should use [androidx.compose.ui.text.AnnotatedStringText].
//     */
//    val title: Any
//    val text: Any
//
//    @get:StringResText
//    val positiveText: Int?
//
//    @get:StringResText
//    val negativeText: Int?
//
//    @get:DrawableRes
//    val icon: Int?
//    val onPositiveClick: (() -> Unit)?
//    val onNegativeClick: (() -> Unit)?
//    val onDismiss: (() -> Unit)?
//    val cancelable: Boolean
//}
//
//data class AlertDialogStateString(
//    override val isVisible: Boolean,
//    override val title: StringText = "",
//    override val text: StringText = "",
//    @StringResText
//    override val positiveText: Int? = R.string.ok,
//    @StringResText
//    override val negativeText: Int? = null,
//    @DrawableRes
//    override val icon: Int? = null,
//    override val onPositiveClick: (() -> Unit)? = null,
//    override val onNegativeClick: (() -> Unit)? = null,
//    override val onDismiss: (() -> Unit)? = null,
//    override val cancelable: Boolean = true
//) : AlertDialogState
//
//data class AlertDialogStateStringRes(
//    override val isVisible: Boolean,
//    @StringResText
//    override val title: Int = R.string.empty,
//    @StringResText
//    override val text: Int = R.string.empty,
//    @StringResText
//    override val positiveText: Int? = R.string.ok,
//    @StringResText
//    override val negativeText: Int? = null,
//    @DrawableRes
//    override val icon: Int? = null,
//    override val onPositiveClick: (() -> Unit)? = null,
//    override val onNegativeClick: (() -> Unit)? = null,
//    override val onDismiss: (() -> Unit)? = null,
//    override val cancelable: Boolean = true
//) : AlertDialogState