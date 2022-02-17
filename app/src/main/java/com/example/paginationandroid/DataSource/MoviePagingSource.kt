package com.example.paginationandroid.DataSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationandroid.model.Movie
import com.example.paginationandroid.network.RetrofitServiceAPI

class MoviePagingSource(
    private val retrofitServiceAPI: RetrofitServiceAPI
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val currentPage = params.key ?: FIRST_PAGE_INDEX
        try {
            val response = retrofitServiceAPI.getDataFromApi(currentPage)
            val  moviesList = response.results
            return LoadResult.Page(
                data = moviesList,
                prevKey = if (currentPage == FIRST_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (moviesList.isEmpty()) null else currentPage +1
            )

//            var nextPageNumber: Int? = null
//            if (response.info.next != null) {
//                val uri = Uri.parse(response.info.next)!!
//                val nextPageQuery = uri.getQueryParameter("page")
//                nextPageNumber = Integer.parseInt(nextPageQuery!!)
//            }



        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}