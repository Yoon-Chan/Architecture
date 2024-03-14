package com.example.booksearchapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.data.repository.BookSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository
): ViewModel()
{
    //paging
//    val favoritePagingBooks: StateFlow<PagingData<Book>> =
//        bookSearchRepository.getFavoritePagingBooks()
//            .cachedIn(viewModelScope)
//            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    private val _favoriteBooks = MutableStateFlow<List<Book>>(emptyList());
    val favoriteBooks = _favoriteBooks.asStateFlow()

    private val disposable = CompositeDisposable()

    init {
        disposable.add(
            bookSearchRepository.getFavoriteBooks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _favoriteBooks.value = it
                }, {
                    Log.d("BookSearchApp", "getFavoriteBooks error ${it.message}")
                })
        )
    }

    //데이터 베이스 사용(Room)
    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("DEBUG", "saveBook")
        disposable.add(
            bookSearchRepository.insertBooks(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("BookSearchApp", "insert Complete")
                    },
                    {
                        Log.d("BookSearchApp", "insert error ${it.message}")
                    }
                )
        )
    }

    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        disposable.add(
            bookSearchRepository.deleteBooks(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("BookSearchApp", "delete Complete")
                    },
                    {
                        Log.d("BookSearchApp", "delete error ${it.message}")
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}