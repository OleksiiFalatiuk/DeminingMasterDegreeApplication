package com.example.deminingapplication.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import androidx.navigation.fragment.findNavController
import com.example.deminingapplication.R
import com.example.deminingapplication.ui.auth.compose.AuthUserDataScreen

class AuthUserDataFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AuthUserDataScreen {
                viewModel.insertUserData(
                    it,
                    success = {
                        findNavController().navigate(R.id.action_authUserDataFragment_to_homeFragment)
                    },
                    error = {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    }
}