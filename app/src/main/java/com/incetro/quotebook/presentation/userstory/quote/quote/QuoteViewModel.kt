package com.incetro.quotebook.presentation.userstory.quote.quote

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

class QuoteViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
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
            onCreate = { }
        )

    override fun onBackPressed() {
        router.exit()
    }

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<QuoteViewModel> {
        override fun create(handle: SavedStateHandle): QuoteViewModel
    }
}