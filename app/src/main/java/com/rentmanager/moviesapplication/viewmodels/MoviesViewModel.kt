package com.rentmanager.moviesapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rentmanager.moviesapplication.compose.stateApiResult
import com.rentmanager.moviesapplication.dataclasses.PopularMoviesResponse
import com.rentmanager.moviesapplication.repositories.MovieListRepository

class MoviesViewModel internal constructor(
    private val movieListRepository: MovieListRepository
): ViewModel() {
    val moviesResponse = stateApiResult<PopularMoviesResponse>()

    suspend fun getPopularMovies(page: String) {
        moviesResponse.value = movieListRepository.getPopularMovies(page)
    }

    class Factory(
        private val movieListRepository: MovieListRepository
    ): ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MoviesViewModel(movieListRepository) as T
        }
    }
}