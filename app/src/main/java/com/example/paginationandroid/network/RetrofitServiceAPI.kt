package com.example.paginationandroid.network

import com.example.paginationandroid.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceAPI {

    @GET("character")
    suspend fun getDataFromApi(@Query("page") query: Int): MovieList
}