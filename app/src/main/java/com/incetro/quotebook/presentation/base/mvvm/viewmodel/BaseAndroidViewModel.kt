/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.base.mvvm.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.view.ViewState


abstract class BaseAndroidViewModel<S : ViewState, E : SideEffect>(
    @SuppressLint("StaticFieldLeak") private val app: Application,
    dependencies: BaseViewModelDependencies
) : BaseViewModel<S, E>(dependencies) {

    /**
     * Return the application.
     */
    open fun <T : Application?> getApplication(): T {
        return app as T
    }

    fun getAppContext(): Context = getApplication()
}