package com.example.deminingapplication.ui.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.locale.RoomUserData
import com.example.data.locale.entity.UserEntity
import com.example.data.sharedpreferences.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel(
    private val roomUserData: RoomUserData,
    private val shared: SharedPreferences
) : ViewModel() {
    val firebaseAuthViewModelToken = mutableStateOf("")
    val userData = mutableStateOf<UserEntity?>(null)
    val userDataLiveData = MutableLiveData<UserEntity?>()

    fun insertUserData(userEntity: UserEntity, success: () -> Unit, error: () -> Unit) {
        viewModelScope.launch {
            val value = userEntity.apply {
                firebaseAuthToken = shared.token
            }
            val result = roomUserData.insertUserData(
                value
            )
            result.fold(
                onSuccess = {
                    success()
                },
                onFailure = {
                    error()
                }
            )
        }
    }

    fun getUserData(error: () -> Unit) {
        viewModelScope.launch {
            val result = roomUserData.getUserData(shared.token)
            result.fold(
                onSuccess = {
                    userData.value = it
                },
                onFailure = {
                    error()
                }
            )
        }
    }

    fun getUserDataLiveData(token: String) {
        viewModelScope.launch {
            val result = roomUserData.getUserData(token)
            result.fold(
                onSuccess = {
                    userDataLiveData.value = it
                },
                onFailure = {
                    userDataLiveData.value = null
                }
            )
        }
    }
}