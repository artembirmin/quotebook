/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.lifecycle.SavedStateHandle
import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.common.navigation.Screens
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.interactor.QuoteInteractor
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModel
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModelDependencies
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.INITIAL_STATE_KEY
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.ViewModelAssistedFactory
import com.incetro.quotebook.presentation.userstory.quote.quote.QuoteFragmentViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class QuoteListViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val router: AppRouter,
    private val quoteInteractor: QuoteInteractor,
    baseViewModelDependencies: BaseViewModelDependencies
) : BaseViewModel<QuoteListViewState, SideEffect>(baseViewModelDependencies) {

    override val container: Container<QuoteListViewState, SideEffect> =
        container(
            initialState = savedStateHandle.get<QuoteListViewState>(INITIAL_STATE_KEY)
                ?: QuoteListViewState(),
            savedStateHandle = savedStateHandle,
            buildSettings = {
                exceptionHandler = coroutineExceptionHandler
            },
            onCreate = { getQuotes() }
        )

    fun getQuotes() = intent {
        quoteInteractor.observeQuotes().collect {
            reduce {
                state.copy(quiteItems = it)
            }
        }
    }

    fun onSearch(query: String) = intent {
        reduce {
            val searchResult = state.quiteItems.filter {
                it.content.contains(
                    other = query,
                    ignoreCase = true
                )
            }
            state.copy(searchQuery = query, searchResult = searchResult)
        }
    }

    override fun onBackPressed() {
        router.exit()
    }

    fun onQuoteClick(quote: Quote) {
        router.navigateTo(
            Screens.QuoteScreen(
                QuoteFragmentViewState(
                    quoteId = quote.id
                )
            )
        )
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<QuoteListViewModel> {
        override fun create(handle: SavedStateHandle): QuoteListViewModel
    }
}