package com.example.booksearchapp.data.api

import com.example.booksearchapp.data.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchApi {

    @GET("v3/search/book")
    fun searchBooks(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Single<SearchResponse>
}