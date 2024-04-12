package com.example.booksearchapp.data.repository

import androidx.paging.*
import com.example.booksearchapp.data.model.*
import kotlinx.coroutines.flow.*
import retrofit2.*

class FakeBookSearchRepository : BookSearchRepository {

    private val bookItems = mutableListOf<Book>()

    override suspend fun searchBooks(query: String, sort: String, page: Int, size: Int): Response<SearchResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun insertBooks(book: Book) {
        bookItems.add(book)
    }

    override suspend fun deleteBooks(book: Book) {
        bookItems.remove(book)
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return flowOf(bookItems)
    }

    override suspend fun saveSortMode(mode: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getSortMode(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCacheDeleteMode(mode: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getCacheDeleteMode(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
        TODO("Not yet implemented")
    }

    override fun searchBooksPaging(query: String, sort: String): Flow<PagingData<Book>> {
        TODO("Not yet implemented")
    }
}