package com.example.aquatrack.feature_auth.presentation.ui.forgot_password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.aquatrack.feature_auth.presentation.viewmodel.AuthViewModel

@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
){
    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()



}