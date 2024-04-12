package com.example.booksearchapp.ui.viewmodel

import androidx.test.filters.MediumTest
import com.example.booksearchapp.data.model.*
import com.example.booksearchapp.data.repository.*
import com.google.common.truth.Truth.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test

@MediumTest
class BookViewModelTest{

    private lateinit var viewModel: BookViewModel

    @Before
    fun setUp() {
        viewModel = BookViewModel(FakeBookSearchRepository())
    }


    @Test
    fun save_book_test() = runTest {
        val book = Book(
            listOf("A"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )
        runBlocking {
            viewModel.saveBook(book)
        }
        val favoriteBooks = viewModel.favoriteBooks.first()
        assertThat(favoriteBooks).contains(book)
    }
}