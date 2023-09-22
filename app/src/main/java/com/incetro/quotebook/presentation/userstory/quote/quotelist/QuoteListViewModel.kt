/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.lifecycle.SavedStateHandle
import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModel
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModelDependencies
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.INITIAL_STATE_KEY
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.ViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container

class QuoteListViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val router: AppRouter,
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
            onCreate = { }
        )

    override fun onBackPressed() {
        router.exit()
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<QuoteListViewModel> {
        override fun create(handle: SavedStateHandle): QuoteListViewModel
    }
}