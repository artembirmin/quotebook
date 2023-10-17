package com.incetro.quotebook.presentation.userstory.quote.quote


import androidx.lifecycle.SavedStateHandle
import com.incetro.quotebook.R
import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.model.interactor.QuoteInteractor
import com.incetro.quotebook.presentation.base.messageshowing.AlertDialogState
import com.incetro.quotebook.presentation.base.messageshowing.DialogString
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModel
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModelDependencies
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.INITIAL_STATE_KEY
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.ViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
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

    fun onQuoteContentInput(content: String) = blockingIntent {
        reduce { state.copy(content = content) }
    }

    fun onQuoteAuthorInput(authorName: String) = blockingIntent {
        reduce { state.copy(authorName = authorName) }
    }

    fun onQuoteSourceInput(source: String) = blockingIntent {
        reduce { state.copy(source = source) }
    }

    fun onRefreshCategoriesClick() {
        intent {
            reduce { state.copy(categoriesLoading = true) }
            val newCategories = quoteInteractor.fetchCategories(state.getQuote())
            reduce { state.copy(categoriesLoading = false, categories = newCategories) }
        }
    }

    fun onChangeBackgroundClick() {
        intent {
            reduce {
                state.copy(showBackgroundBottomSheet = true)
            }
        }
    }

    fun onChangeSheetVisibility(isVisible: Boolean) {
        intent {
            reduce {
                state.copy(showBackgroundBottomSheet = isVisible)
            }
        }
    }

    fun onBackgroundSelected(backgroundId: Int) {
        intent {
            reduce {
                state.copy(backgroundBrushId = backgroundId)
            }
        }
    }

    override fun onBackPressed() {
        intent {
            if (state.categoriesLoading) {
                reduce {
                    state.copy(
                        dialogState = AlertDialogState(
                            isVisible = true,
                            title = DialogString.StringResText(R.string.quote_exit_while_categories_fetching_dialog_title),
                            text = DialogString.StringResText(R.string.quote_exit_while_categories_fetching_dialog_text),
                            positiveText = R.string.cancel,
                            negativeText = R.string.exit,
                            onNegativeClick = {
                                router.exit()
                            },
                            onPositiveClick = {
                                intent {
                                    reduce {
                                        state.copy(dialogState = AlertDialogState(isVisible = false))
                                    }
                                }
                            }
                        )
                    )
                }
            } else if (state.showBackgroundBottomSheet) {
                reduce {
                    state.copy(showBackgroundBottomSheet = false)
                }
            } else {
                quoteInteractor.updateQuote(state.getQuote())
                router.exit()
            }
        }
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<QuoteViewModel> {
        override fun create(handle: SavedStateHandle): QuoteViewModel
    }
}