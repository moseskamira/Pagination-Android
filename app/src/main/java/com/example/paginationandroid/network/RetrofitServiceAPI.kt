package com.example.paginationandroid.network

import com.example.paginationandroid.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceAPI {

    @GET("character")
    suspend fun getDataFromApi(@Query("page") query: Int): Response<MovieResponse>
}