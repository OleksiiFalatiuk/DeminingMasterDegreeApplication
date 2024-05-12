package com.example.data.locale

import androidx.room.*
import com.example.data.locale.dao.DeminingDao
import com.example.data.locale.dao.UserDao
import com.example.data.locale.entity.DeminingEntity
import com.example.data.locale.entity.UserEntity

@Database(
    entities = [UserEntity::class, DeminingEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)

abstract class AppDataBase: RoomDatabase() {
    abstract fun getUser(): UserDao
    abstract fun getDemining(): DeminingDao
}