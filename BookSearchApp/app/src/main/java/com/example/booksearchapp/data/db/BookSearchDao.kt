package com.example.booksearchapp.data.db

import androidx.paging.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksearchapp.data.model.Book
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface BookSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book) : Completable

    @Delete
    fun deleteBook(book: Book) : Completable

    @Query("SELECT * FROM books")
    fun getFavoriteBooks(): Flowable<List<Book>>

    @Query("SELECT * FROM books")
    fun getFavoritePagingBooks() : PagingSource<Int, Book>
}
