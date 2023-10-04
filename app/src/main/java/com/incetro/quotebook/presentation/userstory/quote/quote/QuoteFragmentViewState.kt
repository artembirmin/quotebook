/*
 * ProjectTemplate
 *
 * Created by artembirmin on 12/8/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quote

import android.os.Parcelable
import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.presentation.base.messageshowing.AlertDialogState
import com.incetro.quotebook.presentation.base.mvvm.view.LoaderState
import com.incetro.quotebook.presentation.base.mvvm.view.ViewState
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.joda.time.DateTime

@Parcelize
data class QuoteFragmentViewState(
    val quoteId: Long = 0,
    val content: String = "",
    val authorName: String = "",
    val categories: List<Category> = emptyList(),
    val source: String = "",
    val dateTime: DateTime = DateTime.now(),
    @IgnoredOnParcel override var dialogState: AlertDialogState = AlertDialogState(),
    @IgnoredOnParcel override var loaderState: LoaderState = LoaderState()
) : ViewState(), Parcelable {
    override fun copyState(): QuoteFragmentViewState = this.copy()
}