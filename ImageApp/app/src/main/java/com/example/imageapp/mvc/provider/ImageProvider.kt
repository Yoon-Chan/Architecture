package com.example.imageapp.mvc.provider

import com.example.imageapp.ImageResponse
import com.example.imageapp.RetrofitManager
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class ImageProvider(private val callback: Callback) {

    fun getRandomImage() {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                  if(response.isSuccessful){
                      response.body()?.let {
                          callback.loadImage(it.urls.regular, it.color)
                      }
                  }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {

                }
            })
    }

    interface Callback {
        fun loadImage(url: String, color: String)
    }
}