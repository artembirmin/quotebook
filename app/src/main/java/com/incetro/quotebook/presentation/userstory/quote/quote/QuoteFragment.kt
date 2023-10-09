package com.incetro.quotebook.presentation.userstory.quote.quote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.incetro.quotebook.R
import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.presentation.base.mvvm.view.BaseComposeFragment
import com.incetro.quotebook.presentation.base.mvvm.view.getInitParams
import com.incetro.quotebook.presentation.base.mvvm.view.provideInitParams
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.SavedStateViewModelFactoryImpl
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.lazyViewModelByFactory
import com.incetro.quotebook.presentation.ui.theme.AppTheme
import com.incetro.quotebook.presentation.userstory.quote.di.QuoteComponent
import com.incetro.quotebook.presentation.userstory.quote.quotelist.CategoryItem
import org.joda.time.DateTime
import org.orbitmvi.orbit.compose.collectAsState
import javax.inject.Inject

class QuoteFragment : BaseComposeFragment() {

    @Inject
    lateinit var viewModelFactory: QuoteViewModel.Factory

    private val _viewModel: QuoteViewModel by lazyViewModelByFactory {
        SavedStateViewModelFactoryImpl(
            this,
            viewModelFactory,
            getInitParams<QuoteFragmentViewState>()
        )
    }

    override fun getViewModel(): QuoteViewModel = _viewModel

    override fun inject() = QuoteComponent.Manager.getComponent().inject(this)

    override fun release() = Unit

    @Composable
    override fun CreateView() {
        val viewState: QuoteFragmentViewState by _viewModel.collectAsState()
        AppTheme {
            QuoteInfoContent(
                viewState,
                onBackPressed = _viewModel::onBackPressed,
                onContentInput = _viewModel::onQuoteContentInput,
                onAuthorInput = _viewModel::onQuoteAuthorInput,
                onSourceInput = _viewModel::onQuoteSourceInput,
                onRefreshCategoriesClick = _viewModel::onRefreshCategoriesClick,
                onChangeBackgroundClick = _viewModel::onChangeBackgroundClick
            )
        }
    }

    override fun onBackPressed() {
        _viewModel.onBackPressed()
    }

    companion object {
        fun newInstance(initialState: QuoteFragmentViewState) =
            QuoteFragment().provideInitParams(initialState) as QuoteFragment
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
private fun QuoteInfoContent(
    viewState: QuoteFragmentViewState,
    onBackPressed: () -> Unit,
    onContentInput: (String) -> Unit,
    onAuthorInput: (String) -> Unit,
    onSourceInput: (String) -> Unit,
    onRefreshCategoriesClick: () -> Unit,
    onChangeBackgroundClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            Icons.Default.ArrowBack,
                            "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(value = viewState.content,
                    onValueChange = onContentInput,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Quote") }
                )

                OutlinedTextField(
                    value = viewState.authorName,
                    onValueChange = onAuthorInput,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Author") }
                )

                OutlinedTextField(
                    value = viewState.source,
                    onValueChange = onSourceInput,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Source") }
                )

                if (viewState.categories.isNotEmpty() || viewState.categoriesLoading) {
                    Text(
                        text = "Categories",
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                if (viewState.categoriesLoading) {
                    Text(
                        text = stringResource(id = R.string.quote_categories_loading_title),
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    FlowRow(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        viewState.categories.forEach { CategoryItem(categoryName = it.name) }
                    }
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(onClick = onRefreshCategoriesClick) {
                    Text("Refresh categories")
                }
                OutlinedButton(onClick = onChangeBackgroundClick) {
                    Text("Change background")
                }
            }
        }
    }
}

@Preview
@Composable
fun QuoteInfoPreview() {
    AppTheme {
        QuoteInfoContent(
            viewState = QuoteFragmentViewState(
                quoteId = 5369,
                content = "augue",
                authorName = "Dianne Kerr",
                categories = listOf(
                    Category(id = 4631, name = "Nina Holder"),
                    Category(id = 8433, name = "Cleo Mason"),
                    Category(id = 6749, name = "Wanda Houston")
                ),
                source = "",
                dateTime = DateTime.now(),
            ),
            onBackPressed = {},
            onContentInput = {},
            onAuthorInput = {},
            onSourceInput = {},
            onRefreshCategoriesClick = {},
            onChangeBackgroundClick = {},
        )
    }
}