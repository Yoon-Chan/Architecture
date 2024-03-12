package com.example.booksearchapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import androidx.work.WorkManager
import com.example.booksearchapp.BuildConfig
import com.example.booksearchapp.data.api.BookSearchApi
import com.example.booksearchapp.data.db.BookSearchDatabase
import com.example.booksearchapp.util.Constant.BASE_URL
import com.example.booksearchapp.util.Constant.DATASTORE_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", "KakaoAK ${BuildConfig.KAKAO_API_KEY}")
                .build()
            chain.proceed(request)
        }
    }


    //Retrofit
    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            ))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BookSearchApi {
        return retrofit.create(BookSearchApi::class.java)
    }


    //Room
    @Singleton
    @Provides
    fun provideBookSearchDatabase(@ApplicationContext context: Context): BookSearchDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            BookSearchDatabase::class.java,
            "favorite-books"
        ).build()


    //DataStore
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(DATASTORE_NAME) }
        )


    //WorkManager
    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)


    @Singleton
    @Provides
    fun provideCacheDeleteResult(): String = "Cache has delete by HIlt"
}