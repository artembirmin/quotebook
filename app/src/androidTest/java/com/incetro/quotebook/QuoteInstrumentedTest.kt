/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.incetro.quotebook.entity.quote.Author
import com.incetro.quotebook.entity.quote.Category
import com.incetro.quotebook.entity.quote.Quote
import com.incetro.quotebook.model.repository.quote.QuoteRepository
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
class QuoteInstrumentedTest {

    @Inject
    lateinit var quoteRepository: QuoteRepository

    @Before
    fun setupTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val testComponent = DaggerTestComponent.builder()
            .testModule(QuoteTestModule(appContext))
            .build()

        testComponent.inject(this)
    }

    @Test
    fun createNewQuote() {
        runTest {
            val newQuote = quoteRepository.createNewQuote()
            println("newQuote = $newQuote")
            val newQuoteFromDb = quoteRepository.getQuote(newQuote.id)
            println("newQuoteFromDb = $newQuoteFromDb")
            Assert.assertEquals(newQuote, newQuoteFromDb)
        }
    }

    @Test
    fun editNewQuote() {
        runTest {
            val newQuote = quoteRepository.createNewQuote()
            val editedQuote = newQuote.copy(
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
            quoteRepository.updateQuote(editedQuote)
            val editedQuoteFromDb = quoteRepository.getQuote(editedQuote.id)
            val areContentTheSame = compareQuotesContent(editedQuote, editedQuoteFromDb)
            Assert.assertTrue(areContentTheSame)
        }
    }

    @Test
    fun deleteExistsQuote() {
        runTest {
            val quote = createQuote()
            quoteRepository.deleteQuote(quote)

            try {
                quoteRepository.getQuote(quote.id)
            } catch (e: Exception) {
                Assert.assertTrue(e is NullPointerException)
            }
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
                    Category(name = "1"),
                    Category(name = "3")
                )
            )
            quoteRepository.updateQuote(editedQuote)
            val editedQuoteFromDb = quoteRepository.getQuote(existsQuote.id)
            val areContentTheSame = compareQuotesContent(editedQuote, editedQuoteFromDb)
            println("editedQuote = $editedQuote")
            println("editedQuoteFromDb = $editedQuoteFromDb")
            Assert.assertTrue(areContentTheSame)
        }
    }

    private suspend fun createQuote(): Quote {
        val quote = quoteRepository.createNewQuote()
        quoteRepository.updateQuote(
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
        return quoteRepository.getQuote(quote.id)
    }

    private fun compareQuotesContent(
        editedQuote: Quote,
        editedQuoteFromDb: Quote
    ) = (editedQuote.id == editedQuoteFromDb.id
            && editedQuote.content == editedQuoteFromDb.content
            && editedQuote.source == editedQuoteFromDb.source
            && editedQuote.categories compareNamesWith editedQuoteFromDb.categories
            && editedQuote.author?.name == editedQuoteFromDb.author?.name)

    private infix fun List<Category>.compareNamesWith(categories: List<Category>): Boolean {
        if (this.size != categories.size) return false
        if (this.isEmpty() && categories.isEmpty()) return true
        return this.map { it1 -> categories.indexOfFirst { it.name == it1.name } }
            .all { it != -1 }
    }
}