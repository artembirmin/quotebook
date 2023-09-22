/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.incetro.quotebook.presentation.base.mvvm.view.BaseComposeFragment
import com.incetro.quotebook.presentation.base.mvvm.view.getInitParams
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.SavedStateViewModelFactoryImpl
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.lazyViewModelByFactory
import com.incetro.quotebook.presentation.ui.theme.AppTheme
import com.incetro.quotebook.presentation.userstory.quote.di.QuoteComponent
import org.orbitmvi.orbit.compose.collectAsState
import javax.inject.Inject

class QuoteListFragment : BaseComposeFragment() {

    @Inject
    lateinit var viewModelFactory: QuoteListViewModel.Factory

    private val _viewModel: QuoteListViewModel by lazyViewModelByFactory {
        SavedStateViewModelFactoryImpl(
            this,
            viewModelFactory,
            getInitParams<QuoteListViewState>()
        )
    }

    override fun getViewModel(): QuoteListViewModel = _viewModel

    override fun inject() = QuoteComponent.Manager.getComponent().inject(this)

    override fun release() = Unit

    @Preview
    @Composable
    override fun CreateView() {
        val viewState: QuoteListViewState by _viewModel.collectAsState()
        AppTheme {

        }
    }

    override fun onBackPressed() {
        _viewModel.onBackPressed()
    }

    companion object {
        fun newInstance() = QuoteListFragment()
    }
}