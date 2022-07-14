package com.rentmanager.moviesapplication

import android.content.Context
import com.rentmanager.moviesapplication.repositories.MovieListRepository
import com.rentmanager.moviesapplication.viewmodels.MoviesViewModel

private lateinit var InjectorUtils: Injector

fun getInjector(context: Context): Injector {
    if (!::InjectorUtils.isInitialized) {
        InjectorUtils = Injector(context)
    }

    return InjectorUtils
}

class Injector(context: Context) {
    private val moviesRepository by lazy { MovieListRepository }

    fun provideMovieViewModelFactory(): MoviesViewModel.Factory {
        return MoviesViewModel.Factory(moviesRepository)
    }
}