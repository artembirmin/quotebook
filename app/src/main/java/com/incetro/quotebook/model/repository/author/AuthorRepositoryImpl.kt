/*
 * Quotebook
 *
 * Created by artembirmin on 20/9/2023.
 */

package com.incetro.quotebook.model.repository.author

import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.model.data.database.author.AuthorDao
import com.incetro.quotebook.model.data.database.author.AuthorDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorRepositoryImpl @Inject constructor(
    private val authorDao: AuthorDao
) : AuthorRepository {


    override suspend fun updateOrCreateAuthor(author: Author): Author {

        val currentAuthor: AuthorDto? = authorDao.getAuthorByName(author.name)
        val authorId = currentAuthor?.let {
            authorDao.update(it.copy(name = author.name))
            currentAuthor.id
        } ?: authorDao.insert(
            AuthorDto(
                name = author.name
            )
        )

        return authorDao.getAuthorById(authorId).toAuthor()
    }
}
