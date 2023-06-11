package org.idnp.jetpackpagingsample.paging

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.model.AppDatabase
import org.idnp.jetpackpagingsample.model.CountryRepository

class CountryViewModel(context: Context) : ViewModel() {

    private val countryRepository: CountryRepository = CountryRepository(AppDatabase.getInstance(context))

    fun items(): Flow<PagingData<Country>> {

        val pager = Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 3)
        ) {
            CountryPagingSource(countryRepository)
        }.flow.cachedIn(viewModelScope)

        return pager
    }

    suspend fun insertCountries(countries: List<Country>) {
        countryRepository.insertCountries(countries)
    }
}