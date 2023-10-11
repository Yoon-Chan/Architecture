package com.example.imageapp.mvvm.repository

import com.example.imageapp.mvvm.model.Image
import io.reactivex.Single

interface ImageRepository {

    fun getRandomImage() : Single<Image>
}