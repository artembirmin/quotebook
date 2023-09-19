/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.database.author

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.quotebook.entity.quote.Author

@Entity(
    tableName = AuthorDto.TABLE_NAME
)
data class AuthorDto(
    @PrimaryKey
    val id: String,
    val name: String
) {
    fun toAuthor(): Author = Author(id, name)

    companion object {
        const val TABLE_NAME = "author"
    }
}