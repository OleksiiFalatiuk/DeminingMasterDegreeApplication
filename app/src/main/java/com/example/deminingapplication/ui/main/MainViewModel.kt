package com.example.deminingapplication.ui.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.locale.RoomDeminingData
import com.example.data.locale.entity.DeminingEntity
import kotlinx.coroutines.launch
import java.util.Random

class MainViewModel(
    private val roomDeminingData: RoomDeminingData
): ViewModel() {
    var userGeo: String = ""
    var deminingData = mutableStateListOf<DeminingEntity>()

    fun insertDeminingData(token: String, currentDate: String, deminingDescription: String,warningLevel: String, onSuccess:() -> Unit, onFailure:() -> Unit){
        viewModelScope.launch {
            val random = (Long.MIN_VALUE..Long.MIN_VALUE).random().toString()
            val result = roomDeminingData.insertDeminingData(
                DeminingEntity(
                    appealId = random,
                    firebaseAuthToken = token,
                    userGeo = userGeo,
                    currentDate = currentDate,
                    deminingDescription = deminingDescription,
                    warningLevel = warningLevel
                )
            )
            result.fold(
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                    onFailure()
                }
            )
        }
    }

    fun getDeminingData(token: String, onFailure:() -> Unit){
        viewModelScope.launch {
            val result = roomDeminingData.getDeminingData(token)
            result.fold(
                onSuccess = {
                    Log.d("TAG888", it.toString())
                    deminingData.clear()
                    deminingData.addAll(it.toMutableStateList())
                    Log.d("TAG888", "123 ---> $deminingData")
                },
                onFailure = {
                    onFailure()
                }
            )
        }
    }
}