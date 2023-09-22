/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.quote

import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.data.database.author.AuthorDao
import com.incetro.quotebook.model.data.database.author.AuthorDto
import com.incetro.quotebook.model.data.database.category.CategoryDao
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import com.incetro.quotebook.model.data.database.quote.QuoteDto
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    private val quoteDao: QuoteDao,
    private val categoryDao: CategoryDao,
    private val authorDao: AuthorDao
) : QuoteRepository {

    override suspend fun createNewQuote(): Quote {
        val writingDate = DateTime.now()
        val newQuoteId = quoteDao.insert(QuoteDto(writingDate = writingDate))
        return Quote(id = newQuoteId.toInt(), writingDate = writingDate)
    }

    override suspend fun getQuote(quoteId: Int): Quote {
        return quoteDao.getById(quoteId).toQuote()
    }

    override suspend fun updateQuote(quote: Quote): Quote {
        val authorId = updateAuthor(quote.author)

        val currentCategories = quoteDao.getById(quote.id).categories.map { it.toCategory() }
        val editedCategories = quote.categories

        val oldCategories =
            currentCategories.filterNot { current -> editedCategories.find { current.name == it.name } != null }

        val newCategories =
            editedCategories.filterNot { current -> currentCategories.find { current.name == it.name } != null }

        Timber.e("oldCategories = $oldCategories")
        Timber.e("newCategories = $newCategories")

        newCategories.forEach {
            Timber.e("add category $it")
            categoryDao.addCategory(it.name, quote.id)
        }

        oldCategories.forEach {
            categoryDao.delete(it.toDto())
        }


        quoteDao.update(QuoteDto(quote.id, quote.content, quote.source, authorId))
        return quoteDao.getById(quote.id).toQuote()
    }

    private suspend fun updateAuthor(
        author: Author?
    ): Int? {
        return if (author?.name == null) {
            null
        } else {
            val currentAuthor: AuthorDto? = authorDao.getAuthorByName(author.name)
            if (currentAuthor != null) {
                authorDao.update(currentAuthor.copy(name = author.name))
                currentAuthor.id
            } else {
                authorDao.insert(
                    AuthorDto(
                        name = author.name
                    )
                ).toInt()
            }
        }
    }

    override suspend fun deleteQuote(quote: Quote) {
        quoteDao.deleteById(quote.id.toLong())
    }
}
