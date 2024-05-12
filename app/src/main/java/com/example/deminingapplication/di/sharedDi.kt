package com.example.deminingapplication.di

import com.example.data.sharedpreferences.SharedPreferences
import org.koin.dsl.module

val sharedDI = module {
    single<SharedPreferences> {
        SharedPreferences(
            get()
        )
    }
}