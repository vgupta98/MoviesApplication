package com.rentmanager.moviesapplication.repositories

import com.rentmanager.moviesapplication.dataclasses.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieListService {
    @POST("/3/movie/popular?api_key=5ca7f1911bc6b0ed0747087ba0907739&language=en-US?page={page}")
    suspend fun getPopularMovies(@Path("page") page: String): Response<PopularMoviesResponse>
}