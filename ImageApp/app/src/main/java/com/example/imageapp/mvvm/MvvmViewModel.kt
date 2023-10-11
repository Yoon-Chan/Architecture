package com.example.imageapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imageapp.mvvm.model.Image
import com.example.imageapp.mvvm.repository.ImageRepository
import io.reactivex.disposables.CompositeDisposable

class MvvmViewModel(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _countLiveData = MutableLiveData<String>()
    val countLiveData: LiveData<String> = _countLiveData

    private val _imageLiveData = MutableLiveData<Image>()
    val imageLiveData: LiveData<Image> = _imageLiveData


    private var disposable: CompositeDisposable? = CompositeDisposable()
    private var count = 0

    fun loadRandomImage() {
        disposable?.addAll(imageRepository.getRandomImage()
            .doOnSuccess {
                count++
            }
            .subscribe { item ->
                _imageLiveData.value = item
                _countLiveData.value = "불러온 이미지 수 : $count"
            }
        )
    }

    override fun onCleared() {
        disposable = null
        super.onCleared()
    }


    class MvvmViewModelFactory(private val imageRepository: ImageRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MvvmViewModel(imageRepository) as T
        }
    }
}