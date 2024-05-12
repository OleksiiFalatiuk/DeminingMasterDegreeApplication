package com.example.deminingapplication.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.data.sharedpreferences.SharedPreferences
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import com.example.deminingapplication.ui.auth.AuthViewModel
import com.example.deminingapplication.ui.history.compose.HistoryScreen
import com.example.deminingapplication.ui.main.MainViewModel

class HistoryFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModel<MainViewModel>()
    private val authViewModel: AuthViewModel by activityViewModel<AuthViewModel>()
    private val sharedPreferences by lazy { SharedPreferences(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            HistoryScreen(viewModel.deminingData)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDeminingData(sharedPreferences.token) {
            Toast.makeText(requireContext(), "Sorry, something went wrong", Toast.LENGTH_SHORT)
                .show()
        }
    }
}