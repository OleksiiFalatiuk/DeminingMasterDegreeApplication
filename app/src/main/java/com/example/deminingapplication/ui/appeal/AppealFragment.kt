package com.example.deminingapplication.ui.appeal

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import androidx.navigation.fragment.findNavController
import com.example.data.sharedpreferences.SharedPreferences
import com.example.deminingapplication.ui.appeal.compose.AppealScreen
import com.example.deminingapplication.ui.auth.AuthViewModel
import com.example.deminingapplication.ui.main.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class AppealFragment : Fragment() {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private val viewModel: MainViewModel by activityViewModel<MainViewModel>()
    private val authViewModel: AuthViewModel by activityViewModel<AuthViewModel>()
    private val sharedPreferences by lazy { SharedPreferences(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AppealScreen { desc, warning ->
                viewModel.insertDeminingData(
                    deminingDescription = desc,
                    warningLevel = warning,
                    token = sharedPreferences.token,
                    currentDate = getDateTime() ?: "",
                    onSuccess = {
                        findNavController().navigateUp()
                    },
                    onFailure = {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        loadCoordinates()
    }

    private fun loadCoordinates() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location: Location? ->
                viewModel.userGeo = "${location?.latitude},${location?.longitude}"
            }
    }

    private fun getDateTime(): String? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        return dateFormat.format(date)
    }
}