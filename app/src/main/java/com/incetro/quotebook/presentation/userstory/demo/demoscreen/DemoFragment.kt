/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.demo.demoscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.incetro.quotebook.presentation.base.mvvm.view.BaseComposeFragment
import com.incetro.quotebook.presentation.base.mvvm.view.getInitParams
import com.incetro.quotebook.presentation.base.mvvm.view.provideInitParams
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.SavedStateViewModelFactoryImpl
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.lazyViewModelByFactory
import com.incetro.quotebook.presentation.ui.theme.AppTheme
import com.incetro.quotebook.presentation.userstory.demo.di.DemoComponent
import org.orbitmvi.orbit.compose.collectAsState
import javax.inject.Inject

class DemoFragment : BaseComposeFragment() {

    @Inject
    lateinit var viewModelFactory: DemoViewModel.Factory

    private val _viewModel: DemoViewModel by lazyViewModelByFactory {
        SavedStateViewModelFactoryImpl(
            this,
            viewModelFactory,
            getInitParams<DemoFragmentViewState>()
        )
    }

    override fun getViewModel(): DemoViewModel = _viewModel

    override fun inject() = DemoComponent.Manager.getComponent().inject(this)

    override fun release() = Unit

    @Preview
    @Composable
    override fun CreateView() {
        val viewState: DemoFragmentViewState by _viewModel.collectAsState()
        AppTheme {

        }
    }

    override fun onBackPressed() {
        _viewModel.onBackPressed()
    }

    companion object {
        fun newInstance(initialState: DemoFragmentViewState) =
            DemoFragment().provideInitParams(initialState) as DemoFragment
    }
}