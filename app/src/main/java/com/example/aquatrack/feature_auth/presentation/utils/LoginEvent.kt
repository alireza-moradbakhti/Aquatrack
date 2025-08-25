package com.example.aquatrack.feature_auth.presentation.utils

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class TogglePasswordVisibility(val isVisible: Boolean) : LoginEvent()
    data class RememberMeChanged(val rememberMe: Boolean) : LoginEvent()
    object LoginButtonClicked : LoginEvent()
    object GoogleSignInClicked : LoginEvent()
    object ForgotPasswordClicked : LoginEvent()
}