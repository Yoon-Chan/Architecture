package com.example.booksearchapp.data.repository

import androidx.paging.PagingData
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.data.model.SearchResponse
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
    suspend fun insertBooks(book: Book)

    suspend fun deleteBooks(book: Book)

    fun getFavoriteBooks(): Flow<List<Book>>

    //DataStore
    suspend fun saveSortMode(mode: String)

    suspend fun getSortMode(): Flow<String>


    suspend fun saveCacheDeleteMode(mode: Boolean)

    suspend fun getCacheDeleteMode(): Flow<Boolean>

    //paging
    fun getFavoritePagingBooks(): Flow<PagingData<Book>>

    //fun searchBooksPaging(query: String, sort: String): Flow<PagingData<Book>>


}