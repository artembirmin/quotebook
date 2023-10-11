/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.quoteinteractor

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.incetro.quotebook.entity.category.Category
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.interactor.QuoteInteractor
import com.incetro.quotebook.model.repository.quote.QuoteFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class QuoteInteractorInstrumentedTest {

    @Inject
    lateinit var quoteInteractor: QuoteInteractor

    private val quoteFactory = QuoteFactory()

    @Before
    fun setupTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val testComponent = DaggerQuoteTestComponent.builder()
            .testModule(QuoteTestModule(appContext))
            .build()

        testComponent.inject(this)
    }

    @Test
    fun createEmptyQuote() {
        runTest {
            val newQuote = quoteInteractor.createEmptyQuote()
            println("newQuote = $newQuote")
            val newQuoteFromDb = quoteInteractor.getQuote(newQuote.id)
            println("newQuoteFromDb = $newQuoteFromDb")
            Assert.assertEquals(newQuote, newQuoteFromDb)
        }
    }

    @Test
    fun createNewQuote() {
        runTest {
            val emptyQuote = quoteInteractor.createEmptyQuote()
            val newQuote = emptyQuote.copy(
                content = "odio",
                source = "fuisset",
                author = Author(name = "Statham"),
                writingDate = DateTime.now(),
                categories = listOf(
                    Category(name = "1"),
                    Category(name = "2"),
                    Category(name = "3")
                )
            )
            quoteInteractor.updateQuote(newQuote)
            val editedQuoteFromDb = quoteInteractor.getQuote(newQuote.id)
            val areContentTheSame = compareQuotesContent(newQuote, editedQuoteFromDb)
            Assert.assertTrue(areContentTheSame)
        }
    }

    @Test
    fun addManyQuotes() {
        runTest {
            val quotes = listOf<Quote>(
                quoteFactory.getQuote(),
                quoteFactory.getQuote(),
                quoteFactory.getQuote(),
                quoteFactory.getQuote(),
            )
            quoteInteractor.addQuotes(quotes)
            val addedQuotes = quoteInteractor.observeQuotes().first().sortedBy { it.writingDate }
            val contentCompare = quotes.mapIndexed { index, quote ->
                println("index = $index quote = $quote,\n addedQuote = ${addedQuotes[index]}")
                compareQuotesContent(
                    quote.copy(
                        categories = quote.categories.toMutableList().distinctBy { it.name }),
                    addedQuotes[index]
                )
            }
            Assert.assertTrue(contentCompare.all { it })
        }
    }

    @Test(expected = NullPointerException::class)
    fun deleteExistsQuote() {
        runTest {
            val quote = createQuote()
            quoteInteractor.deleteQuote(quote)
            quoteInteractor.getQuote(quote.id)
        }
    }

    @Test
    fun editExistsQuote() {
        runTest {
            val existsQuote = createQuote()
            val editedQuote = existsQuote.copy(
                content = "wertgvcds",
                source = "dfghbvderg",
                author = Author(name = "dftgvdsdfg"),
                writingDate = DateTime.now(),
                categories = listOf(
                    Category(name = "3"),
                    Category(name = "1"),
                    Category(name = "4"),
                    Category(name = "5"),
                )
            )
            quoteInteractor.updateQuote(editedQuote)
            val editedQuoteFromDb = quoteInteractor.getQuote(existsQuote.id)
            val areContentTheSame = compareQuotesContent(editedQuote, editedQuoteFromDb)
            println("editedQuote = $editedQuote")
            println("editedQuoteFromDb = $editedQuoteFromDb")
            Assert.assertTrue(areContentTheSame)
        }
    }

    @Test
    fun clearExistsQuote() {
        runTest {
            val existsQuote = createQuote()
            val editedQuote = existsQuote.copy(
                content = "",
                source = "",
                author = Author(name = ""),
                writingDate = DateTime.now(),
                categories = emptyList()
            )
            quoteInteractor.updateQuote(editedQuote)
            val editedQuoteFromDb = quoteInteractor.getQuote(existsQuote.id)
            val areContentTheSame = compareQuotesContent(editedQuote, editedQuoteFromDb)
            println("editedQuote = $editedQuote")
            println("editedQuoteFromDb = $editedQuoteFromDb")
            Assert.assertTrue(areContentTheSame)
        }
    }

    private suspend fun createQuote(): Quote {
        val quote = quoteInteractor.createEmptyQuote()
        quoteInteractor.updateQuote(
            quote.copy(
                content = "odio",
                source = "fuisset",
                author = Author(name = "Statham"),
                writingDate = DateTime.now(),
                categories = listOf(
                    Category(name = "1"),
                    Category(name = "2"),
                    Category(name = "3")
                )
            )
        )
        return quoteInteractor.getQuote(quote.id)
    }

    private fun compareQuotesContent(
        editedQuote: Quote,
        editedQuoteFromDb: Quote
    ): Boolean {
        Assert.assertEquals(editedQuote.content, editedQuoteFromDb.content)
        Assert.assertEquals(editedQuote.source, editedQuoteFromDb.source)
        Assert.assertTrue(editedQuote.categories compareNamesWith editedQuoteFromDb.categories)
        Assert.assertEquals(editedQuote.author.name, editedQuoteFromDb.author.name)
        return true
    }

    private infix fun List<Category>.compareNamesWith(categories: List<Category>): Boolean {
        if (this.size != categories.size) return false
        if (this.isEmpty() && categories.isEmpty()) return true
        return this.map { it1 -> categories.indexOfFirst { it.name == it1.name } }
            .all { it != -1 }
    }
}