package com.rentmanager.moviesapplication.repositories

import com.rentmanager.moviesapplication.dataclasses.PopularMoviesResponse
import com.rentmanager.moviesapplication.retrofit.ApiResult
import com.rentmanager.moviesapplication.retrofit.MoviesAPI
import com.rentmanager.moviesapplication.retrofit.apiCall

object MovieListRepository {

    suspend fun getPopularMovies(page: String): ApiResult<PopularMoviesResponse> {
        return apiCall { MoviesAPI.moviesService.getPopularMovies(page) }
    }
}