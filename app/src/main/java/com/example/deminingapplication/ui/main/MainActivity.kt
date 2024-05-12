package com.example.deminingapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deminingapplication.R
import com.example.deminingapplication.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val authViewModel by viewModel<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}