package com.example.booksearchapp.data.repository

import androidx.paging.PagingData
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.data.model.SearchResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface BookSearchRepository {
    fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Single<SearchResponse>


    //Room
    fun insertBooks(book: Book) : Completable

    fun deleteBooks(book: Book): Completable

    fun getFavoriteBooks(): Flowable<List<Book>>

    //DataStore
    suspend fun saveSortMode(mode: String)

    suspend fun getSortMode(): Flow<String>


    suspend fun saveCacheDeleteMode(mode: Boolean)

    suspend fun getCacheDeleteMode(): Flow<Boolean>

    //paging
    fun getFavoritePagingBooks(): Flowable<PagingData<Book>>


    fun searchBooksPaging(query: String, sort: String): Flowable<PagingData<Book>>


}