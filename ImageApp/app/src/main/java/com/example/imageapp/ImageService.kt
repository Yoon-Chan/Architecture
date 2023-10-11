package com.example.imageapp

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {
    @Headers("Authorization: Client-ID ezGhjcqULF3WLOLfGTV2-U9SrRE88sN1L1XS3AdrV1Q")
    @GET("photos/random")
    fun getRandomImage(): Call<ImageResponse>


    @Headers("Authorization: Client-ID ezGhjcqULF3WLOLfGTV2-U9SrRE88sN1L1XS3AdrV1Q")
    @GET("photos/random")
    fun getRandomImageRx(): Single<ImageResponse>
}