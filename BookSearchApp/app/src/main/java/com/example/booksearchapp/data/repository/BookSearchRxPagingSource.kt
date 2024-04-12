package com.example.booksearchapp.data.repository

import android.net.http.HttpException
import androidx.paging.*
import androidx.paging.rxjava3.*
import com.example.booksearchapp.data.api.*
import com.example.booksearchapp.data.model.*
import com.example.booksearchapp.util.Constant.PAGING_SIZE
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException

class BookSearchRxPagingSource(
    private val api: BookSearchApi,
    private val query: String,
    private val sort: String,
) : RxPagingSource<Int, Book>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Book>> {
        val pageNumber = params.key ?: STARTING_PAGE_INDEX

        return api.searchBooks(query,sort, pageNumber, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map {
                try {
                    val prevKey = if(pageNumber == STARTING_PAGE_INDEX) null else pageNumber -1
                    val nextKey = if(it.meta.isEnd ?: false) null else pageNumber + (params.loadSize / PAGING_SIZE)

                    LoadResult.Page(
                        data = it.documents ?: emptyList(),
                        prevKey = prevKey,
                        nextKey = nextKey )
                }catch (e: IOException) {
                    LoadResult.Error(e)
                }catch (e: Exception){
                    LoadResult.Error(e)
                }
            }
    }


    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

}