package com.example.booksearchapp.data.api

import com.example.booksearchapp.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance {
//    private val okHttpClient : OkHttpClient by lazy {
//        val httpLoggingInterceptor =  HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    private val retrofit : Retrofit by lazy {
//        Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .baseUrl(BASE_URL)
//            .build()
//    }
//
//    val api : BookSearchApi by lazy {
//        retrofit.create(BookSearchApi::class.java)
//    }
//}