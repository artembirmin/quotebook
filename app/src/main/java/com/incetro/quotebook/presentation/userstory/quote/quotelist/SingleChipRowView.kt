/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.incetro.quotebook.entity.quote.Category
import timber.log.Timber

@Preview
@Composable
fun ChipsRowPreview() {
    val categories = listOf(
        Category(1, "Work"),
        Category(2, "Jason"),
        Category(2, "Andrew"),
        Category(2, "Green"),
        Category(2, "Artemis"),
        Category(2, "Coleman"),
        Category(2, "Ronnie"),
        Category(2, "1Ronnie"),
        Category(2, "2Ronnie"),
        Category(2, "3Ronnie"),
    )
    ChipsRow(chipList = categories.map { it.name })
}

@Composable
fun ChipsRow(chipList: List<String>) {
    var totalWidth by remember { mutableStateOf(0.dp) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { layoutInfo ->
                totalWidth = layoutInfo.size.width.dp
            }
    ) {
        var remainingWidth = totalWidth
        var chipIndex = 0

        while (chipIndex < chipList.size && remainingWidth > totalWidth * 0.8f) {
            val chipText = chipList[chipIndex]
            val chipWidth = calculateTextWidth(chipText)

            Timber.e("totalWidth = $totalWidth, " +
                    "remainingWidth = $remainingWidth, " +
                    "chipWidth = $chipWidth")

            if (chipWidth <= remainingWidth) {
                Timber.e("addChip $chipText")
                CategoryItem(chipText)
                remainingWidth -= chipWidth + 8.dp // Учитываем отступ
            } else {
                Timber.e("break")
                // Этот чип не помещается, прекращаем добавление
                return@Row
            }

            chipIndex++
        }
    }
}

@Composable
fun calculateTextWidth(text: String): Dp {
    // Здесь вы можете реализовать логику для вычисления ширины текста в Dp
    // Например, можно использовать measureText() из Paint для этой цели
    // Замените эту функцию на вашу реализацию
    return text.length * 20.dp // Пример: каждый символ имеет ширину 10.dp
}

@Composable
fun CategoryItem(text: String) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = ShapeDefaults.Small
            )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
        )
    }
}