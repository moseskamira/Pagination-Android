package com.example.paginationandroid.DataSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationandroid.model.Movie
import com.example.paginationandroid.network.RetrofitServiceAPI

class MoviePagingSource(
    val retrofitServiceAPI: RetrofitServiceAPI
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
       return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val response = retrofitServiceAPI.getDataFromApi(nextPage)

            var nextPageNumber: Int? = null
            if (response.info.next != null) {
                val uri = Uri.parse(response.info.next)!!
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = Integer.parseInt(nextPageQuery!!)

            }
            return LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }


}