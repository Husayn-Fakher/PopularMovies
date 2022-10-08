package com.example.popularmovies.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://api.themoviedb.org"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MoviesAPIService {

    @GET("/3/movie/popular?api_key=4c2818f72ea1d539b281bee480037321&language=en-US&page=352")
    suspend fun  getMovies() : MovieDetails


}

object MoviesAPI {
    val retrofitService : MoviesAPIService by lazy {
        retrofit.create(MoviesAPIService::class.java) }
}