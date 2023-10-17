/*
 * Quotebook
 *
 * Created by artembirmin on 12/10/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quote

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.incetro.quotebook.presentation.ui.theme.AppTheme
import com.incetro.quotebook.presentation.ui.theme.ExtendedTheme
import com.incetro.quotebook.presentation.ui.theme.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectBackgroundBottomSheetContent(
    backgroundBrushId: Int?,
    onBackgroundSelected: (Int) -> Unit
) {
    Box(
        Modifier
            .height(300.dp)
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 48.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val pagerState = rememberPagerState()
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                onBackgroundSelected(page)
            }
        }
        val scope = rememberCoroutineScope()
        val itemSize = 160.dp
        LaunchedEffect(backgroundBrushId) {
            backgroundBrushId?.let { pagerState.scrollToPage(it) }
        }
        HorizontalPager(
            pageCount = ExtendedTheme.quoteBackgroundBrushes.size + 1,
            state = pagerState,
            pageSize = PageSize.Fixed(itemSize),
            contentPadding = PaddingValues(
                horizontal = (LocalConfiguration.current.screenWidthDp.dp - itemSize) / 2
            ),
            pageSpacing = 8.dp
        ) { page ->
            Card(
                Modifier
                    .size(width = itemSize, height = itemSize + 50.dp)
                    .graphicsLayer {

                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                                .absoluteValue

                        alpha = lerp(
                            start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = lerp(
                            start = 0.9f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            start = 0.9f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        scope.launch {
                            pagerState.animateScrollToPage(page)
                        }
                    },
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            ExtendedTheme.quoteBackgroundBrushes.getBrushById(id = page)
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun BottomSheetPrev() {
    AppTheme(manualTheme = Theme.DARK) {
        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var backgroundBrushId by remember { mutableStateOf<Int?>(null) }
        BottomSheetScaffold(
            sheetContent = {
                SelectBackgroundBottomSheetContent(
                    null,
                    { backgroundBrushId = it },
                )
            },
            scaffoldState = rememberBottomSheetScaffoldState(sheetState),
        ) {
            ScreenContent(scope, sheetState, { }, backgroundBrushId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    scope: CoroutineScope,
    state: SheetState,
    onClick: () -> Unit,
    backgroundBrushId: Int?,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(brush = ExtendedTheme.quoteBackgroundBrushes.getBrushById(id = backgroundBrushId))
    ) {
        Button(
            onClick = {
                onClick()
                scope.launch {
                    if (state.isVisible) {
                        state.hide()
                    } else {
                        state.show()
                    }
                }
            },
        ) {
            if (state.isVisible) {
                Text(text = "CloseBottom Sheet Scaffold")
            } else {
                Text(text = "Open Bottom Sheet Scaffold")
            }
        }
    }
}



