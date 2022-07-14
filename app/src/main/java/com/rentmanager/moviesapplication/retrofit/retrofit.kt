package com.rentmanager.moviesapplication.retrofit

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rentmanager.moviesapplication.repositories.MovieListService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val retrofitGson: Gson by lazy {
    GsonBuilder()
        .disableHtmlEscaping()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}

private val gsonConverterFactory by lazy {
    GsonConverterFactory.create(retrofitGson)
}


private val API_BASE_URL = ""

private var cacheDirProvider: (() -> File)? = null

private fun provideRetrofitCache(): Cache? {
    return cacheDirProvider?.let {
        Cache(it().resolve("retrofit-cache"), 10 * 1024 * 1024)   //10MB)
    }
}


private val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .also {
            provideRetrofitCache()?.let { c ->
                it.cache(c)
            }
        }
        .build()
}

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

inline fun <reified T> create(): T {
    return retrofit.create(T::class.java)
}

object MoviesAPI {
    val moviesService by lazy { create<MovieListService>() }
}

//
//fun clearApiCache() {
//    try {
//        okHttpClient.cache()?.evictAll()
//    } catch (t: Throwable) {
//        Log.e("HttpClient", "error clearing cache", t)
//    }
//}