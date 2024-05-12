package com.example.deminingapplication.di

import com.example.deminingapplication.ui.auth.AuthViewModel
import com.example.deminingapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiDi = module {
    viewModel{
        AuthViewModel(
            roomUserData = get(),
            shared = get()
        )
    }

    viewModel{
        MainViewModel(
            roomDeminingData = get()
        )
    }
}