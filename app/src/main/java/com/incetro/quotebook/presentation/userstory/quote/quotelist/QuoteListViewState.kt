/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.presentation.base.messageshowing.AlertDialogState
import com.incetro.quotebook.presentation.base.mvvm.view.LoaderState
import com.incetro.quotebook.presentation.base.mvvm.view.ViewState
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuoteListViewState(
    val quiteItems: List<Quote> = emptyList(),
    val searchQuery: String = "",
    val searchResult: List<Quote> = emptyList(),
    @IgnoredOnParcel override var dialogState: AlertDialogState = AlertDialogState(),
    @IgnoredOnParcel override var loaderState: LoaderState = LoaderState()
) : ViewState() {
    override fun copyState(): QuoteListViewState = this.copy()
}