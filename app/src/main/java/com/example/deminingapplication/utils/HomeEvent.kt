package com.example.deminingapplication.utils

sealed class HomeEvent {
    data object WriteAnAppeal: HomeEvent()
    data object RequestHistory: HomeEvent()
    data object UserInfo: HomeEvent()
}