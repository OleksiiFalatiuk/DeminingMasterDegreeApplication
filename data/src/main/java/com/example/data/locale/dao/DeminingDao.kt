package com.example.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.locale.entity.DeminingEntity

@Dao
interface DeminingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeminingInfo(user: DeminingEntity)

    @Query("SELECT * FROM demining WHERE demining.firebaseAuthToken = :token")
    suspend fun getDetails(token: String): List<DeminingEntity>
}