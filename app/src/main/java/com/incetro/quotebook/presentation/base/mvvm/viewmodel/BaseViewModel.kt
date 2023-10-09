/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.base.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.incetro.quotebook.presentation.base.messageshowing.AlertDialogState
import com.incetro.quotebook.presentation.base.messageshowing.ErrorAlertStateFactory
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.view.LoaderState
import com.incetro.quotebook.presentation.base.mvvm.view.ViewState
import com.incetro.quotebook.presentation.base.mvvm.view.updateDialog
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import timber.log.Timber


abstract class BaseViewModel<S : ViewState, E : SideEffect>(
    private val dependencies: BaseViewModelDependencies
) : ViewModel(), ContainerHost<S, E> {

    protected val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, error ->
            Timber.e(error)
            intent {
                reduce {
                    val errorAlertState =
                        ErrorAlertStateFactory.handleError(error, dependencies.resourcesManager)
                    state.updateDialog {
                        errorAlertState.copy(onDismiss = { onDismissDialog() })
                    }.apply {
                        loaderState = LoaderState(hasLoading = false)
                    }
                }
            }
        }

    protected fun onDismissDialog() = intent {
        reduce {
            state.updateDialog {
                AlertDialogState()
            }
        }
    }

    open fun onBackPressed() {}

}