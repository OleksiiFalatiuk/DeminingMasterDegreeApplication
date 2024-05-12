package com.example.data.locale

import com.example.data.locale.dao.UserDao
import com.example.data.locale.entity.UserEntity

class RoomUserData(private val userDao: UserDao) {

    suspend fun insertUserData(user: UserEntity): Result<Unit> = runCatching {
        userDao.insertUserInfo(user)
    }

    suspend fun getUserData(token: String): Result<UserEntity> = runCatching {
        userDao.getUserInfo(token)
    }
}