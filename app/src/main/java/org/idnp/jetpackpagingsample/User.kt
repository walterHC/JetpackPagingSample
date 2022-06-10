package org.idnp.jetpackpagingsample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val cui: Int,
    val firstName: String,
    val lastName:String
)