/*
 * Quotebook
 *
 * Created by artembirmin on 20/9/2023.
 */

package com.incetro.quotebook.model.repository.author

import com.incetro.quotebook.entity.quote.Author

interface AuthorRepository {
    suspend fun updateAuthor(author: Author?): Author?
}