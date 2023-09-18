/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.app

import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.common.navigation.Screens
import com.incetro.quotebook.model.preferences.PreferencesManager
import com.incetro.quotebook.presentation.userstory.demo.demoscreen.DemoFragmentViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLauncher @Inject constructor(
    private val router: AppRouter,
    private val preferencesManager: PreferencesManager
) {
    /**
     *  Initialized and launches application.
     */
    fun start() {
        App.theme.value = preferencesManager.appTheme

        router.newRootScreen(Screens.DemoScreen(DemoFragmentViewState()))
    }
}