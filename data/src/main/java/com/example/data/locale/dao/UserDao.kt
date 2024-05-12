package com.example.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.locale.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: UserEntity)

    @Query("SELECT * FROM user WHERE user.firebaseAuthToken = :token")
    suspend fun getUserInfo(token: String): UserEntity
}