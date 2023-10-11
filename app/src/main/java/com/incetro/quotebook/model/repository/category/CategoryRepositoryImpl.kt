/*
 * Quotebook
 *
 * Created by artembirmin on 19/9/2023.
 */

package com.incetro.quotebook.model.repository.category

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import com.incetro.quotebook.entity.category.CategoriesGptRequest
import com.incetro.quotebook.entity.category.Category
import com.incetro.quotebook.entity.category.GptMessage
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.data.database.category.CategoryDao
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import com.incetro.quotebook.model.data.network.api.CategoryApi
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val quoteDao: QuoteDao,
    private val categoryApi: CategoryApi,
    private val gson: Gson
) : CategoryRepository {

    private val minCategoriesCount = 4
    private val maxCategoriesCount = 8
    private val maxCategoryWordsCount = 2
    private val categoriesGptPromt =
        "Ты робот, который составляет базу знаний. Твое текущее задание: распределить цитаты людей по категориям." +
                " Названия категорий должны четко и ясно описывать смысл цитаты." +
                " В дальнейшем по этим категориям будет происходить поиск соответствующих цитат." +
                " Для каждой цитаты тебе нужно написать от $minCategoriesCount до $maxCategoriesCount категорий через запятую." +
                " Максимальная длина категории составляет $maxCategoryWordsCount слова  на языке, на котором написана цитата." +
                " Я буду отправлять тебе цитату, а ты  отправь категории в формате JSON." +
                " Пример ответа, если ты распознал цитату: [\"category1\", \"category2\", \"category3\"]." +
                " Пример ответа, если ты  не распознал цитату: []"

    private val categoriesGptPromtMessage =
        GptMessage(role = "system", content = categoriesGptPromt)

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

        newCategories.forEach {
            Timber.e("add category = $it, quoteId = $quoteId")
            categoryDao.addCategory(it.name, quoteId)
        }

        oldCategories.forEach {
            categoryDao.delete(it.toDto())
        }
    }

    override suspend fun fetchCategories(
        quote: Quote,
        requestCount: Int // = 1 by default
    ): List<Category> {
        val categoriesGptRequest = CategoriesGptRequest(
            messages = listOf(
                categoriesGptPromtMessage,
                GptMessage(role = "user", content = quote.content)
            )
        )
        val categoriesGptResponse = categoryApi.getCategories(categoriesGptRequest)
        val categoriesJson = categoriesGptResponse.choices.first().message.content
        val categoriesTypeToken = object : TypeToken<List<String>>() {}.type

        return try {
            gson
                .fromJson<List<String>>(categoriesJson, categoriesTypeToken)
                .map { Category(name = it) }
        } catch (e: JsonParseException) {
            if (requestCount == 2) {
                emptyList()
            } else {
                fetchCategories(quote = quote, requestCount = requestCount + 1)
            }
        }
    }
}
