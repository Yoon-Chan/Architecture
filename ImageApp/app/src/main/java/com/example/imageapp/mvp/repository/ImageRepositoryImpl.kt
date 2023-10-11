package com.example.imageapp.mvp.repository

import com.example.imageapp.ImageResponse
import com.example.imageapp.RetrofitManager
import retrofit2.Call
import retrofit2.Response

class ImageRepositoryImpl : ImageRepository {
    override fun getRandomImage(callback: ImageRepository.CallBack) {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageResponse>{
                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    response.body()?.let {
                        callback.loadImage(it.urls.regular, it.color)
                    }
                }
            })
    }
}