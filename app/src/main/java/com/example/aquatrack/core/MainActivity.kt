package com.example.aquatrack.core

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.aquatrack.R
import com.example.aquatrack.feature_home.presentation.ui.theme.AquaTrackTheme
import com.example.aquatrack.navigation.RootNavGraph
import com.example.aquatrack.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        val message = if (isGranted) {
            getString(R.string.notification_enabled)
        } else {
            getString(R.string.notification_disabled)
        }
        showMessage(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        askNotificationPermission() // Ask for permission on startup
        enableEdgeToEdge()
        setContent {
            AquaTrackTheme {
                val navController = rememberNavController()
                RootNavGraph(navController = navController)
            }
        }
    }


    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}