package com.example.booksearchapp.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksearchapp.data.api.BookSearchApi
import com.example.booksearchapp.data.db.BookSearchDatabase
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.data.model.SearchResponse
import com.example.booksearchapp.data.repository.BookSearchRepositoryImpl.PreferencesKeys.CACHE_DELETE_MODE
import com.example.booksearchapp.data.repository.BookSearchRepositoryImpl.PreferencesKeys.SORT_MODE
import com.example.booksearchapp.util.Constant.PAGING_SIZE
import com.example.booksearchapp.util.Sort
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BookSearchRepositoryImpl @Inject constructor(
    private val db: BookSearchDatabase,
    private val dataStore: DataStore<Preferences>,
    private val api: BookSearchApi
) : BookSearchRepository {

    override fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Single<SearchResponse> {
        return api.searchBooks(query, sort, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Log.d("BookSearchApp_searchBooks_doOnSuccess", "${it}")
            }
            .doOnError {
                Log.d("BookSearchApp_searchBooks_doOnError", "${it.message}")
            }
            .doOnTerminate {
                Log.d("BookSearchApp_searchBooks_doOnTerminate", "doOnTerminate")
            }
    }


    private object PreferencesKeys {
        val SORT_MODE = stringPreferencesKey("sort_mode")
        val CACHE_DELETE_MODE = booleanPreferencesKey("cache_delete_mode")
    }

    override fun insertBooks(book: Book) : Completable {
        return db.bookSearchDao().insertBook(book)
    }

    override fun deleteBooks(book: Book) : Completable {
        return db.bookSearchDao().deleteBook(book)
    }


    override fun getFavoriteBooks(): Flowable<List<Book>> {
        return db.bookSearchDao().getFavoriteBooks()
    }

    override suspend fun saveSortMode(mode: String) {
        dataStore.edit { prefs ->
            prefs[SORT_MODE] = mode
        }
    }

    override suspend fun getSortMode(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }

            }
            .map { prefs ->
                prefs[SORT_MODE] ?: Sort.ACCURACY.value
            }
    }

//    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
//        val pagingSourceFactory = { db.bookSearchDao().getFavoritePagingBooks() }
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = PAGING_SIZE,
//                enablePlaceholders = false,
//                maxSize = PAGING_SIZE * 3
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }


//    override fun searchBooksPaging(query: String, sort: String): Flow<PagingData<Book>> {
//
//        val pagingSourceFactory = { BookSearchPagingSource(api ,query, sort) }
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = PAGING_SIZE,
//                enablePlaceholders = false,
//                maxSize = PAGING_SIZE * 3
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }

    override suspend fun saveCacheDeleteMode(mode: Boolean) {
        dataStore.edit { prefs ->
            prefs[CACHE_DELETE_MODE] = mode
        }
    }

    override suspend fun getCacheDeleteMode(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    exception.printStackTrace()
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { prefs ->
                prefs[CACHE_DELETE_MODE] ?: false
            }
    }
}

