package com.example.aquatrack.feature_auth.presentation.utils

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val forgotPassword: Boolean = false
)