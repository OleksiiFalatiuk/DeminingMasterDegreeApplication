package com.example.deminingapplication.ui.home

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deminingapplication.R
import com.example.deminingapplication.ui.home.compose.HomeScreen
import com.example.deminingapplication.utils.HomeEvent
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class HomeFragment: Fragment(), EasyPermissions.PermissionCallbacks {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            HomeScreen(::checkHomeEvent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methodRequiresTwoPermission()
    }

    private fun checkHomeEvent(homeEvent: HomeEvent){
        when(homeEvent){
            is HomeEvent.WriteAnAppeal -> {
                findNavController().navigate(R.id.action_homeFragment_to_appealFragment)
            }
            is HomeEvent.RequestHistory -> {
                findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
            }
            is HomeEvent.UserInfo -> {
                findNavController().navigate(R.id.action_homeFragment_to_userFragment)
            }
        }
    }

    @AfterPermissionGranted(RC_LOCATION)
    private fun methodRequiresTwoPermission() {
        val permsSecond = ACCESS_COARSE_LOCATION
        if (EasyPermissions.hasPermissions(requireContext(), permsSecond)) {
            // do something
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this, "",
                RC_LOCATION, permsSecond
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(requireContext(), "Permission was granted", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(requireContext(), "Permission was denied", Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val RC_LOCATION = 1
    }
}