package com.example.deminingapplication.di

import androidx.room.Room
import com.example.data.locale.AppDataBase
import com.example.data.locale.RoomDeminingData
import com.example.data.locale.RoomUserData
import com.example.data.locale.entity.DeminingEntity
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDi = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDataBase::class.java, "Demining.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single{
        val database = get<AppDataBase>()
        database.getUser()
    }

    single{
        val database = get<AppDataBase>()
        database.getDemining()
    }

    factory {
        RoomUserData(
            userDao = get()
        )
    }

    factory {
        RoomDeminingData(
            deminingDao = get()
        )
    }
}