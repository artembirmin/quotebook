/*
 * ProjectTemplate
 *
 * Created by artembirmin on 5/11/2022.
 */

@file:Suppress("FunctionName")

package com.incetro.projecttemplate.common.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.incetro.projecttemplate.presentation.userstory.demo.demoscreen.DemoFragment
import com.incetro.projecttemplate.presentation.userstory.demo.demoscreen.DemoFragmentViewState

/**
 * App screens for navigation with Cicerone.
 */
object Screens {
    fun DemoScreen(initState: DemoFragmentViewState): FragmentScreen =
        FragmentScreen() {
            DemoFragment.newInstance(initState)
        }
}