/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

@file:Suppress("FunctionName")

package com.incetro.quotebook.common.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.incetro.quotebook.presentation.userstory.quote.quotelist.QuoteListFragment
import com.incetro.quotebook.presentation.userstory.quote.quotelist.QuoteListViewState

/**
 * App screens for navigation with Cicerone.
 */
object Screens {
    fun DemoScreen(): FragmentScreen =
        FragmentScreen() {
            QuoteListFragment.newInstance()
        }
}