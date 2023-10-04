package com.incetro.quotebook.presentation.userstory.quote.quote

import androidx.lifecycle.SavedStateHandle
import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.model.interactor.QuoteInteractor
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModel
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModelDependencies


import com.incetro.quotebook.presentation.base.mvvm.viewmodel.INITIAL_STATE_KEY
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.ViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class QuoteViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val quoteInteractor: QuoteInteractor,
    private val router: AppRouter,
    baseViewModelDependencies: BaseViewModelDependencies
) : BaseViewModel<QuoteFragmentViewState, SideEffect>(baseViewModelDependencies) {

    override val container: Container<QuoteFragmentViewState, SideEffect> =
        container(
            initialState = savedStateHandle.get<QuoteFragmentViewState>(INITIAL_STATE_KEY)
                ?: QuoteFragmentViewState(),
            savedStateHandle = savedStateHandle,
            buildSettings = {
                exceptionHandler = coroutineExceptionHandler
            },
            onCreate = { showQuote() }
        )

    fun showQuote() = intent {
        val quote = quoteInteractor.getQuote(state.quoteId)
        reduce {
            state.copy(
                quoteId = quote.id,
                content = quote.content,
                authorName = quote.author?.name ?: "",
                categories = quote.categories,
                source = quote.source,
                dateTime = quote.writingDate
            )
        }
    }

    fun onQuoteContentInput(content: String) = intent {
        reduce { state.copy(content = content) }
    }

    override fun onBackPressed() {
        router.exit()
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<QuoteViewModel> {
        override fun create(handle: SavedStateHandle): QuoteViewModel
    }
}