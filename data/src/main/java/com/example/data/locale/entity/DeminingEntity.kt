package com.example.data.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Demining")
data class DeminingEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val appealId: String,
    val firebaseAuthToken: String,
    val userGeo: String,
    val deminingDescription: String,
    val currentDate: String,
    val warningLevel: String
)
