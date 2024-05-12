package com.example.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {
    private val data: SharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
    var isUserAuth: Boolean by PreferencesDelegate(data, "isUserAuth", false)
    var token: String by PreferencesDelegate(data, "token", "")
}