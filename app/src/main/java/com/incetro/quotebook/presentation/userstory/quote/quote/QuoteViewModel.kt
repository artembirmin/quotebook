package com.incetro.quotebook.presentation.userstory.quote.quote


import androidx.lifecycle.SavedStateHandle
import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.interactor.QuoteInteractor
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModel
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModelDependencies
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.INITIAL_STATE_KEY
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.ViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.joda.time.DateTime
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
        val quoteId = state.quoteId
        val quote = if (quoteId == null) {
            quoteInteractor.createEmptyQuote()
        } else {
            quoteInteractor.getQuote(quoteId)
        }
        reduce {
            state.copy(
                quoteId = quote.id,
                content = quote.content,
                authorName = quote.author.name,
                categories = quote.categories,
                source = quote.source,
                dateTime = quote.writingDate
            )
        }
    }

    fun onQuoteContentInput(content: String) = intent {
        reduce { state.copy(content = content) }
    }

    fun onQuoteAuthorInput(authorName: String) = intent {
        reduce { state.copy(authorName = authorName) }
    }

    fun onQuoteSourceInput(source: String) = intent {
        reduce { state.copy(source = source) }
    }

    fun onRefreshCategoriesClick() {

    }

    fun onChangeBackgroundClick() {

    }

    override fun onBackPressed() {
        intent {
            quoteInteractor.updateQuote(
                Quote(
                    id = state.quoteId ?: 0,
                    content = state.content,
                    source = state.source,
                    categories = state.categories,
                    author = Author(name = state.authorName),
                    writingDate = DateTime.now(),
                )
            )
            router.exit()
        }
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<QuoteViewModel> {
        override fun create(handle: SavedStateHandle): QuoteViewModel
    }
}