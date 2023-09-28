/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.quotelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.entity.quote.Quote
import org.joda.time.DateTime

@Composable
fun QuoteListItem(quote: Quote) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = quote.content,
                modifier = Modifier.padding(bottom = 8.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
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
                CategoryFlowRow(
                    categoryNameList = quote.categories.map { it.name },
                    modifier = Modifier.weight(1f)
                )
                Text(text = quote.writingDate.toString("dd/MM/yyyy"))
            }
        }
    }
}

@Preview
@Composable
fun QuoteListItemPreview() {
    QuoteListItem(
        Quote(
            id = 1,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
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
    CategoryItem("Category")
}