/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.app

import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.common.navigation.Screens
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.data.database.AppDatabase
import com.incetro.quotebook.model.data.preferences.PreferencesManager
import com.incetro.quotebook.model.interactor.QuoteInteractor
import com.incetro.quotebook.model.repository.quote.QuoteFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLauncher @Inject constructor(
    private val router: AppRouter,
    private val preferencesManager: PreferencesManager,
    private val quoteInteractor: QuoteInteractor,
    private val appDatabase: AppDatabase
) {
    /**
     *  Initialized and launches application.
     */
    fun start(coroutineScope: CoroutineScope) {
        App.theme.value = preferencesManager.appTheme
        val quoteFactory = QuoteFactory()

//        appDatabase.clearAllTables()

        coroutineScope.launch {
            val quotes = quoteInteractor.observeQuotes().first()
            if (quotes.isEmpty()) {
                quoteInteractor.addQuotes(
                    mutableListOf<Quote>().apply {
                        repeat(10) {
                            add(quoteFactory.getQuote())
                        }
                    }
                )
            }
        }

        router.newRootScreen(Screens.QuoteListFragment())
    }
}