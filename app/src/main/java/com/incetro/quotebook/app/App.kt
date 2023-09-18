/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.app

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.incetro.quotebook.BuildConfig
import com.incetro.quotebook.common.di.componentmanager.ComponentsManager
import com.incetro.quotebook.presentation.ui.theme.Theme
import com.incetro.quotebook.utils.FileLoggingTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        inject()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.plant(FileLoggingTree(this))
        }
    }

    private fun inject() {
        ComponentsManager.init(this)
        ComponentsManager.getInstance().applicationComponent.inject(this)
    }

    companion object {
        val theme = mutableStateOf(Theme.SYSTEM)
    }
}