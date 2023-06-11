package org.idnp.jetpackpagingsample.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM country WHERE id_country > :start AND id_country < :end")
    suspend fun getCountries(start: Int, end:Int): List<Country>

    @Insert
    suspend fun insert(countries: List<Country>)
}