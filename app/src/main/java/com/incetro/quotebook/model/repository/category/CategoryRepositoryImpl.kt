/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.category

import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.model.data.database.category.CategoryDao
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val quoteDao: QuoteDao,
) : CategoryRepository {

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

}
