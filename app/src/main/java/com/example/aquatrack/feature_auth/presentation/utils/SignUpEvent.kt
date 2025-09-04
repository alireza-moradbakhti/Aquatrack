package com.example.aquatrack.feature_auth.presentation.utils

sealed class SignUpEvent {
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChange(val password: String) : SignUpEvent()
    data class ConfirmPasswordChange(val confirmPassword: String) : SignUpEvent()
    data class TogglePasswordVisibility(val isVisible: Boolean) : SignUpEvent()
    data class ToggleConfirmPasswordVisibility(val isVisible: Boolean) : SignUpEvent()
    data object SignUpClick : SignUpEvent()
    data object GoogleClick : SignUpEvent()
}