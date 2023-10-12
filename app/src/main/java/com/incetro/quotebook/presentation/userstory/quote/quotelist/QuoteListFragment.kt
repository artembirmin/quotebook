/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.incetro.quotebook.R
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.presentation.base.mvvm.view.BaseComposeFragment
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.SavedStateViewModelFactoryImpl
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.lazyViewModelByFactory
import com.incetro.quotebook.presentation.ui.theme.AppTheme
import com.incetro.quotebook.presentation.ui.view.SearchTextField
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
            viewModelFactory
        )
    }

    override fun getViewModel(): QuoteListViewModel = _viewModel

    override fun inject() = QuoteComponent.Manager.getComponent().inject(this)

    override fun release() = Unit

    @Composable
    override fun CreateView() {
        val viewState: QuoteListViewState by _viewModel.collectAsState()
        AppTheme {
            QuoteListContent(
                viewState = viewState,
                fabClickListener = _viewModel::onCreateNewQuoteClick,
                onSearch = _viewModel::onSearch,
                onQuoteClick = _viewModel::onQuoteClick,
            )
        }
    }

    override fun onBackPressed() {
        _viewModel.onBackPressed()
    }

    companion object {
        fun newInstance() = QuoteListFragment()
    }
}

@Composable
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class
)
private fun QuoteListContent(
    fabClickListener: () -> Unit,
    viewState: QuoteListViewState,
    onSearch: (String) -> Unit,
    onQuoteClick: (Quote) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        // https://issuetracker.google.com/issues/249727298
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .systemBarsPadding(),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = fabClickListener,
                modifier = Modifier.size(68.dp),
                shape = ShapeDefaults.Large
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
        ) {
            SearchTextField(
                search = viewState.searchQuery,
                onValueChange = onSearch,
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn(
                modifier = Modifier.imePadding(),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val quotes = if (viewState.searchQuery.isNotBlank()) {
                    viewState.searchResult
                } else {
                    viewState.quiteItems
                }
                items(items = quotes, key = { it.id }) { quote ->
                    QuoteListItem(quote = quote, onClick = onQuoteClick)
                }
            }
        }
    }
}

@Preview
@Composable
fun QuoteListPreview() {
    val date = DateTime.now()
    val author = Author(name = "Author Name")
    val quote =
        "composables provide support for displaying items in a grid. A Lazy vertical grid will display its items in a vertically scrollable container, spanned across multiple columns, while the Lazy horizontal grids will have the same behaviour on the horizontal axis."
    QuoteListContent(
        viewState = QuoteListViewState().copy(
            quiteItems = listOf(
                Quote(id = 0, content = quote.take(10), author = author, writingDate = date),
                Quote(id = 2, content = quote.take(20), author = author, writingDate = date),
                Quote(id = 3, content = quote.take(30), author = author, writingDate = date),
                Quote(id = 4, content = quote.take(40), author = author, writingDate = date),
                Quote(id = 5, content = quote.take(50), author = author, writingDate = date),
                Quote(id = 6, content = quote.take(60), author = author, writingDate = date),
                Quote(id = 7, content = quote.take(70), author = author, writingDate = date),
            )
        ),
        fabClickListener = {}, onSearch = {}, onQuoteClick = {},
    )
}