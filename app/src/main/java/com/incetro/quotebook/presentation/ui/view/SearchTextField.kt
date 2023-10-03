/*
 * Quotebook
 *
 * Created by artembirmin on 2/10/2023.
 */

package com.incetro.quotebook.presentation.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchTextField(
    search: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp
) {
    val textColor = MaterialTheme.colorScheme.onSecondary
    val backgroundColor = MaterialTheme.colorScheme.secondary
    BasicTextField(
        value = search,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = fontSize,
            color = textColor
        ),
        cursorBrush = SolidColor(textColor),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .then(modifier)
                    .background(
                        backgroundColor,
                        MaterialTheme.shapes.medium
                    )
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = textColor,
                    )
                    Box(
                        modifier = Modifier.height(20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (search.isEmpty()) {
                            Text(
                                text = "Search",
                                style = TextStyle(
                                    fontSize = fontSize,
                                    color = textColor.copy(alpha = 0.6f),
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = false
                                    )
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    var value by remember { mutableStateOf("") }
//    OutlinedTextField(value = "werfdsr", onValueChange = {})
    SearchTextField(
        search = value,
        onValueChange = { value = it },
        modifier = Modifier.padding(20.dp)
    )
}