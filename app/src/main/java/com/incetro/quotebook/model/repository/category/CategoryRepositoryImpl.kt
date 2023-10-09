/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.category

import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.data.database.category.CategoryDao
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import com.incetro.quotebook.model.repository.quote.QuoteFactory
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val quoteDao: QuoteDao,
) : CategoryRepository {

    private val quoteFactory = QuoteFactory()

    override suspend fun updateCategories(quoteId: Long, editedCategories: List<Category>) {
        val currentCategories = quoteDao.getQuoteById(quoteId).categories.map { it.toCategory() }

        val oldCategories =
            currentCategories.filterNot { current ->
                editedCategories.find {
                    current.name == it.name
                } != null
            }

        val newCategories = editedCategories
            .filterNot { current ->
                currentCategories.find {
                    current.name == it.name
                } != null
            }.distinctBy { it.name }

        Timber.e("quoteId = $quoteId, currentCategories = $currentCategories")
        Timber.e("oldCategories = $oldCategories")
        Timber.e("newCategories = $newCategories")
        newCategories.forEach {
            Timber.e("add category = $it, quoteId = $quoteId")
            categoryDao.addCategory(it.name, quoteId)
        }

        oldCategories.forEach {
            categoryDao.delete(it.toDto())
        }
    }

    override suspend fun fetchCategories(quote: Quote): List<Category> {
        delay(1000)
        val categoriesCount = Random.nextInt(from = 4, until = 8)
        return quoteFactory.getCategory(categoriesCount).distinctBy { it.name }
    }
}
