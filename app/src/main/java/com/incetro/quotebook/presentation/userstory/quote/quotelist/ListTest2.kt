/*
 * Quotebook
 *
 * Created by artembirmin on 2/10/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import timber.log.Timber
import kotlin.math.abs

/**
 * https://medium.com/@debdut.saha.1/top-app-bar-animation-using-nestedscrollconnection-like-facebook-jetpack-compose-b446c109ee52
 */
@Preview
@Composable
fun ScrollSyncButtonDemo1() {
    var searchText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val offsetY = remember { Animatable(0f) }

    // Обновляем анимацию при изменении scrollState
    LaunchedEffect(listState) {
        Timber.e("listState = $listState")
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { offset ->
                Timber.e("offset = $offset")
//                offsetY.animateTo(-offset.toFloat(), animationSpec = spring())
            }
    }
    val nestedScrollState =
        rememberNestedScrollConnection(onOffsetChanged = {}, appBarHeight = 0f)
    LaunchedEffect(key1 = Unit, block = {
//        onOffsetChanged(pixelValue)
    })

    Column {

        // Список заметок
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .nestedScroll(nestedScrollState),
            state = listState,
            contentPadding = PaddingValues(top = 56.dp) // Отступ для списка, чтобы учесть высоту поисковой панели
        ) {
            items(100) { index ->
                Text(
                    text = "Item $index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }

    // Кнопка, которая перемещается с прокруткой
    Button(
        onClick = { /* Действие при нажатии кнопки */ },
        modifier = Modifier
            .offset(y = offsetY.value.dp)
            .fillMaxWidth()
            .background(Color.Blue)
    ) {
        Text("Кнопка")
    }
}

@Composable
fun rememberNestedScrollConnection(onOffsetChanged: (Float) -> Unit, appBarHeight: Float) =
    remember {
        var currentHeight = appBarHeight
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                currentHeight = (currentHeight + available.y).coerceIn(
                    minimumValue = 0f,
                    maximumValue = appBarHeight
                )
                return if (abs(currentHeight) == appBarHeight || abs(currentHeight) == 0f) {
                    super.onPreScroll(available, source)
                } else {
                    onOffsetChanged(currentHeight)
                    available
                }
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                if (available.y < 0) {
                    onOffsetChanged(0f)
                } else {
                    onOffsetChanged(appBarHeight)
                }
                return super.onPreFling(available)
            }
        }
    }