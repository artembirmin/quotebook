/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.presentation.base.mvvm.view.BaseComposeFragment
import com.incetro.quotebook.presentation.base.mvvm.view.getInitParams
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.SavedStateViewModelFactoryImpl
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.lazyViewModelByFactory
import com.incetro.quotebook.presentation.ui.theme.AppTheme
import com.incetro.quotebook.presentation.userstory.quote.di.QuoteComponent
import org.joda.time.DateTime
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

    @Composable
    override fun CreateView() {
        val viewState: QuoteListViewState by _viewModel.collectAsState()
        Screen(viewState)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(viewState: QuoteListViewState) {
        AppTheme {
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    LargeTopAppBar(
                        title = {
                            Text(
                                text = "Цитатникъ",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { innerPadding ->
                LazyColumn(
                    Modifier.padding(innerPadding),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewState.quiteItems) { quote ->
                        QuoteListItem(quote = quote)
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun ScreenPreview() {
        val date = DateTime.now()
        val author = Author(name = "Author Name")
        val quote =
            "composables provide support for displaying items in a grid. A Lazy vertical grid will display its items in a vertically scrollable container, spanned across multiple columns, while the Lazy horizontal grids will have the same behaviour on the horizontal axis."
        Screen(
            viewState = QuoteListViewState().copy(
                quiteItems = listOf(
                    Quote(content = quote.take(10), author = author, writingDate = date),
                    Quote(content = quote.take(20), author = author, writingDate = date),
                    Quote(content = quote.take(30), author = author, writingDate = date),
                    Quote(content = quote.take(40), author = author, writingDate = date),
                    Quote(content = quote.take(50), author = author, writingDate = date),
                    Quote(content = quote.take(60), author = author, writingDate = date),
                    Quote(content = quote.take(70), author = author, writingDate = date),
                )
            )
        )
    }

    override fun onBackPressed() {
        _viewModel.onBackPressed()
    }

    companion object {
        fun newInstance() = QuoteListFragment()
    }
}