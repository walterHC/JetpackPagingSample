package org.idnp.jetpackpagingsample

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException

class ExamplePagingSource(
    val backendService: ExampleBackendService
) : PagingSource<Int, User>() {

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nextPageNumber = params.key ?: 1
            val users = backendService.searchUsers(nextPageNumber)

            return LoadResult.Page(
                data = users ?: listOf(),
                prevKey = null,
                nextKey = null
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}