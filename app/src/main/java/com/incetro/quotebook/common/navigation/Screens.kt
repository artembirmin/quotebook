/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

@file:Suppress("FunctionName")

package com.incetro.quotebook.common.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.incetro.quotebook.presentation.userstory.quote.quote.QuoteFragment
import com.incetro.quotebook.presentation.userstory.quote.quote.QuoteFragmentViewState
import com.incetro.quotebook.presentation.userstory.quote.quotelist.QuoteListFragment

/**
 * App screens for navigation with Cicerone.
 */
object Screens {
    fun QuoteListFragment(): FragmentScreen =
        FragmentScreen() {
            QuoteListFragment.newInstance()
        }

    fun QuoteScreen(initParams: QuoteFragmentViewState): FragmentScreen =
        FragmentScreen() {
            QuoteFragment.newInstance(initParams)
        }
}