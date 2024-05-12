package com.example.deminingapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.data.sharedpreferences.SharedPreferences
import com.example.deminingapplication.Constants
import com.example.deminingapplication.R
import com.example.deminingapplication.ui.auth.compose.AuthScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AuthFragment: Fragment() {

    private val sharedPreferences by lazy { SharedPreferences(requireContext()) }
    private val viewModel: AuthViewModel by activityViewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return ComposeView(requireContext()).apply {
            setContent {
                AuthScreen {
                    initGoogleAuth()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers(){
        viewModel.userDataLiveData.observe(viewLifecycleOwner){
            it?.let {
                findNavController().navigate(R.id.action_authFragment_to_homeFragment)
            } ?: run{
                findNavController().navigate(R.id.action_authFragment_to_authUserDataFragment)
            }
        }
    }

    private fun initGoogleAuth(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, /* p1 = */ null)
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                sharedPreferences.isUserAuth = task.isSuccessful
                if (task.isSuccessful) {
                    sharedPreferences.token = idToken
                    // Sign in success, update UI with the signed-in user's information
                    viewModel.getUserDataLiveData(idToken)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(requireActivity(), "Авторизація не успішна, будь ласка спробуйте ще раз", Toast.LENGTH_SHORT).show()
                }
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken ?: "")
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(requireActivity(), "Авторизація не успішна, будь ласка спробуйте ще раз", Toast.LENGTH_SHORT).show()
            }
        }
    }
}