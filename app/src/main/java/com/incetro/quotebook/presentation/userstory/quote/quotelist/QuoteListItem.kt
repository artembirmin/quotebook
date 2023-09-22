/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.entity.quote.Quote
import org.joda.time.DateTime
import timber.log.Timber

@Composable
fun QuoteListItem(quote: Quote) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = quote.content, modifier = Modifier.padding(bottom = 8.dp))
            Text(
                text = quote.author?.name ?: "Неизвестен",
                modifier = Modifier.align(Alignment.End),
                textAlign = TextAlign.Left,
                fontStyle = FontStyle.Italic
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LazyRow(
                    modifier = Modifier
                        .weight(1f)
                        .height(IntrinsicSize.Min)
                ) {
                    items(quote.categories) { category ->
                        CategoryItem(text = category.name)
                    }
                }
                Text(text = quote.writingDate.toString("dd/MM/yyyy"))
            }
        }
    }
}

@Preview
@Composable
fun LazyRowPreview() {
    val categories = listOf(
        Category(1, "Work"),
        Category(2, "Jason"),
        Category(2, "Andrew"),
        Category(2, "Green"),
        Category(2, "Artemis"),
        Category(2, "Coleman"),
        Category(2, "Ronnie"),
    )
    LazyRow {
        items(categories) {
            Text(it.name, fontSize = 20.sp, modifier = Modifier.padding(4.dp))
        }
    }
}





@Preview
@Composable
fun QuoteListItemPreview() {
    QuoteListItem(
        Quote(
            id = 1,
            content = "Write tests",
            source = "Instagram",
            author = Author(id = 1, name = "Jason Statham"),
            writingDate = DateTime.now(),
            categories = listOf(
                Category(1, "Work"),
                Category(2, "Jason"),
                Category(2, "Andrew"),
                Category(2, "Green"),
                Category(2, "Artemis"),
                Category(2, "Coleman"),
                Category(2, "Ronnie"),
            )
        )
    )
}

@Preview
@Composable
fun CategoryItemPreview() {
//    CategoryItem("Category")
}