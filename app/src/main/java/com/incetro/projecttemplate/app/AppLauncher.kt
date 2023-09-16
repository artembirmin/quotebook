/*
 * ProjectTemplate
 *
 * Created by artembirmin on 6/11/2022.
 */

package com.incetro.projecttemplate.app

import com.incetro.projecttemplate.common.navigation.AppRouter
import com.incetro.projecttemplate.common.navigation.Screens
import com.incetro.projecttemplate.model.preferences.PreferencesManager
import com.incetro.projecttemplate.presentation.userstory.demo.demoscreen.DemoFragmentViewState
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

        router.newRootScreen(Screens.DemoScreen(DemoFragmentViewState(screenNo = 1)))
    }
}