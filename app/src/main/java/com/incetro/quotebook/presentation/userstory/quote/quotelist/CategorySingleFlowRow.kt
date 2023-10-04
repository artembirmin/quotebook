/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.utils.ext.pxToDp

@Preview
@Composable
fun ChipsRowPreview() {
    val categories = listOf(
        Category(1, "Work"),
        Category(2, "Jason"),
        Category(2, "Andrew"),
        Category(2, "Green"),
        Category(2, "Artemis"),
        Category(2, "Col"),
        Category(2, "Re"),
        Category(2, "1Ronnie"),
        Category(2, "2Ronnie"),
        Category(2, "3Ronnie"),
    )
//    val categories = mutableListOf<Category>().apply {
//        repeat( 20) {
//            add(Category(1, "iiii"))
//        }
//    }

    Column {
        Box(
            Modifier
                .size(300.dp, 20.dp)
                .background(color = Color.DarkGray)
        )
        CategoryFlowRow(categoryNameList = categories.map { it.name })
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryFlowRow(categoryNameList: List<String>, modifier: Modifier = Modifier) {
    var totalWidthPx by remember { mutableStateOf(0) }

    FlowRow(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutInfo ->
                    totalWidthPx = layoutInfo.size.width
                }
        )

    ) {
        val totalWidthDp = totalWidthPx.pxToDp()
        var remainingWidthDp = totalWidthDp
        var categoryIndex = 0
        while (categoryIndex < categoryNameList.size && remainingWidthDp > 0.dp) {
            val chipText = categoryNameList[categoryIndex]
            val chipWidthDp = calculateCategoryWidth(chipText)
            if (chipWidthDp <= remainingWidthDp) {
                CategoryItem(chipText)
                remainingWidthDp -= chipWidthDp
            }
            categoryIndex++
        }
    }
}

private val outerPaddingHorizontal = 6.dp
private val innerPaddingHorizontal = 6.dp

@Composable
fun CategoryItem(categoryName: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = outerPaddingHorizontal)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = ShapeDefaults.Small
            )
    ) {
        Text(
            text = categoryName,
            fontSize = getCategoryFontSize(),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = innerPaddingHorizontal, vertical = 1.dp)
        )
    }
}

@Composable
fun getCategoryFontSize(): TextUnit = MaterialTheme.typography.bodyLarge.fontSize

@Composable
fun calculateCategoryWidth(text: String): Dp {
    return text.length * getCategoryFontSize().value.dp * 0.7f +
            outerPaddingHorizontal * 2 + innerPaddingHorizontal * 2
}