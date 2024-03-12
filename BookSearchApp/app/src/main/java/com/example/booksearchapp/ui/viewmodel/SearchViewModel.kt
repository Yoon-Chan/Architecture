package com.example.booksearchapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.data.repository.BookSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }

    private val _searchResult = MutableStateFlow<List<Book>>(emptyList())
    val searchResult: SharedFlow<List<Book>> = _searchResult.asSharedFlow()

    private val _searchPagingResult = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<Book>> = _searchPagingResult.asStateFlow()

    private val disposable = CompositeDisposable()

    var query = String()
        set(value) {
            field = value
            savedStateHandle[SAVE_STATE_KEY] = value
        }

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            disposable.add(
                bookSearchRepository.searchBooks(query, getSortMode(), 1, 50)
                    .subscribe({
                        _searchResult.value = it.documents
                    }, {
                        Log.d("BookSearchApp", "searchBooks Error ${it.message}")
                    })
            )
        }
    }


//    fun searchBooksPaging(query: String) {
//        viewModelScope.launch {
//            bookSearchRepository.searchBooksPaging(query, getSortMode())
//                .cachedIn(viewModelScope)
//                .collect {
//                    _searchPagingResult.value = it
//                }
//        }
//    }

    private suspend fun getSortMode() = withContext(Dispatchers.IO) {
        bookSearchRepository.getSortMode().first()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}