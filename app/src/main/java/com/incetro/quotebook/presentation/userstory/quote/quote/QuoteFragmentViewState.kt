/*
 * ProjectTemplate
 *
 * Created by artembirmin on 12/8/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quote

import android.os.Parcelable
import com.incetro.quotebook.entity.category.Category
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.presentation.base.messageshowing.AlertDialogState
import com.incetro.quotebook.presentation.base.mvvm.view.LoaderState
import com.incetro.quotebook.presentation.base.mvvm.view.ViewState
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.joda.time.DateTime

@Parcelize
data class QuoteFragmentViewState(
    val quoteId: Long? = null,
    val content: String = "",
    val authorName: String = "",
    val categories: List<Category> = emptyList(),
    val source: String = "",
    val dateTime: DateTime = DateTime.now(),
    val categoriesLoading: Boolean = false,
    @IgnoredOnParcel override var dialogState: AlertDialogState = AlertDialogState(),
    @IgnoredOnParcel override var loaderState: LoaderState = LoaderState()
) : ViewState(), Parcelable {

    override fun copyState(): QuoteFragmentViewState = this.copy()

    fun getQuote(): Quote = Quote(
        id = this.quoteId ?: 0,
        content = this.content,
        categories = this.categories,
        author = Author(name = this.authorName),
        source = this.source,
        writingDate = DateTime.now(),
    )

}