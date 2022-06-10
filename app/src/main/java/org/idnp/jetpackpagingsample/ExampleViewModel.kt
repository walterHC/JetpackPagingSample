package org.idnp.jetpackpagingsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class ExampleViewModel : ViewModel() {
    private val backendService: ExampleBackendService = ExampleBackendService()

    fun items(): Flow<PagingData<User>> {

        val pager = Pager(
            PagingConfig(pageSize = 5, enablePlaceholders = false, prefetchDistance = 3)
        ) {
            ExamplePagingSource(backendService)
        }.flow.cachedIn(viewModelScope)

        return pager

    }

}