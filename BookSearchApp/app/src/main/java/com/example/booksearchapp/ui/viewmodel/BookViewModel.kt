package com.example.booksearchapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.data.repository.BookSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async.Schedule
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("DEBUG", "saveBook")
        disposable.add(
            bookSearchRepository.insertBooks(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("BookSearchApp", "insert Complete")
                },
                    {
                        Log.d("BookSearchApp", "insert error ${it.message}")
                    })
        )
    }
}