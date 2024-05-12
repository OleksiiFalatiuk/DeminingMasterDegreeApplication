package com.example.deminingapplication.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.data.sharedpreferences.SharedPreferences
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import com.example.deminingapplication.ui.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class UserFragment: Fragment() {

    private val authViewModel: AuthViewModel by activityViewModel<AuthViewModel>()
    private val sharedPreferences by lazy { SharedPreferences(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel.getUserData{
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            UserScreen(authViewModel.userData.value){
                sharedPreferences.token = ""
                FirebaseAuth.getInstance().signOut()
                requireActivity().recreate()
            }
        }
    }
}