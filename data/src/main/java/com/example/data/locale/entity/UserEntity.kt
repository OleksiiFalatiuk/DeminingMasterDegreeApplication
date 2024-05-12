package com.example.data.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey
    var firebaseAuthToken: String = "",
    val userName: String,
    val userSurname: String,
    val userCity: String,
    val userPhoneNumber: String,
    val userAge: String,
    val userEmail: String,
)