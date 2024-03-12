package com.example.booksearchapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.booksearchapp.data.api.BookSearchApi
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.util.Constant.PAGING_SIZE
import okio.IOException


//class BookSearchPagingSource (
//    private val api : BookSearchApi,
//    private val query: String,
//    private val sort: String,
//
//) : PagingSource<Int, Book>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
//        return try {
//            val pageNumber = params.key ?: STARTING_PAGE_INDEX
//            val response = api.searchBooks(query, sort, pageNumber, params.loadSize)
//
//            //flow
//            //val endOfPaginationReached = response.body()?.meta?.isEnd ?: false
//
//            val endOfPaginationReached = response.meta?.isEnd ?: false
//
//            val data = response.body()?.documents ?: emptyList()
//            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
//            val nextKey =
//                if (endOfPaginationReached) null else pageNumber + (params.loadSize / PAGING_SIZE)
//
//
//            LoadResult.Page(
//                data = data,
//                prevKey = prevKey,
//                nextKey = nextKey
//            )
//        } catch (e: IOException) {
//            LoadResult.Error(e)
//        } catch (e: HttpException) {
//            LoadResult.Error(e)
//        }
//    }
//
//    companion object {
//        const val STARTING_PAGE_INDEX = 1
//    }
//}