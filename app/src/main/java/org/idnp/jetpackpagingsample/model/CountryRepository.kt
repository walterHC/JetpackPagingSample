package org.idnp.jetpackpagingsample.model

import android.util.Log
import org.idnp.jetpackpagingsample.entities.Country


class CountryRepository(private val appDatabase: AppDatabase) {

    suspend fun getCountries(start: Int, numRows: Int): List<Country> {
        Log.d("nextPageNumber:",start.toString())
        val end = start + numRows
        return appDatabase.countryDao().getCountries(start, end)
    }

    suspend fun insertCountries(countries: List<Country>) {
        appDatabase.countryDao().insert(countries)
    }

}