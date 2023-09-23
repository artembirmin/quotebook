/*
 * Quotebook
 *
 * Created by artembirmin on 23/9/2023.
 */

package com.incetro.quotebook.utils.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }
@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }
