package com.example.paginationandroid.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paginationandroid.DataSource.MoviePagingSource
import com.example.paginationandroid.model.Movie
import com.example.paginationandroid.network.RetrofitInstance
import com.example.paginationandroid.network.RetrofitServiceAPI
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {
    var serviceApi: RetrofitServiceAPI = RetrofitInstance().getRetrofitServiceApi()

    fun returnMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 2, maxSize = 200),
            pagingSourceFactory = { MoviePagingSource(serviceApi) }).flow.cachedIn(viewModelScope)
    }
}