package com.example.aquatrack.feature_auth.presentation.utils

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isTermsAccepted: Boolean = false,
    val isPasswordMatch: Boolean = false,
    val isEmailValid: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
