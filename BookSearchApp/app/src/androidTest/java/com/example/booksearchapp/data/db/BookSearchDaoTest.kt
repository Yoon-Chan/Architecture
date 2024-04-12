package com.example.booksearchapp.data.db

import androidx.room.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.booksearchapp.data.model.*
import com.google.common.truth.Truth.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookSearchDaoTest {

    private lateinit var database: BookSearchDatabase
    private lateinit var dao: BookSearchDao

    @Before
    fun setUp() {
        //inMemoryDatabaseBuilder : 메모리 안에서만 Room을 생성
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BookSearchDatabase::class.java
            //메인 스레드에서의 쿼리를 금지하고 있는데 이 데이터베이스에서의 쿼리를 메인 쓰레드에서 실행을 하면 테스트 결과를 예측할 수 없기 때문에
            //allowMainThreadQueries()를 사용하여 메인 스레드에서 쿼리를 수행을 해도 되도록 만들었다.
        ).allowMainThreadQueries().build()
        dao = database.bookSearchDao()
    }


    @After
    fun tearDown() {
        database.close()
    }

    //코루틴 테스트를 이용해 테스트 진행
    //코루틴 테스트를 하고싶은 경우 아래의 의존성을 추가하면 된다.
    //androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")
    @Test
    fun insert_book_to_db() = runTest {
        val book = Book(
            listOf("A"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )
        dao.insertBook(book)

        val favoriteBooks = dao.getFavoriteBooks().first()
        assertThat(favoriteBooks).contains(book)

    }

    //room delete 테스트
    @Test
    fun delete_book_to_db() = runTest {
        val book = Book(
            listOf("A"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )
        dao.insertBook(book)
        dao.deleteBook(book)

        val favoriteBooks = dao.getFavoriteBooks().first()
        assertThat(favoriteBooks).doesNotContain(book)
    }

}