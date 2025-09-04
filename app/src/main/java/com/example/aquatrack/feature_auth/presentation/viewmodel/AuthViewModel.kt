package com.example.aquatrack.feature_auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquatrack.feature_auth.domain.usecase.AuthUseCases
import com.example.aquatrack.feature_auth.presentation.utils.LoginEvent
import com.example.aquatrack.feature_auth.presentation.utils.LoginUiState
import com.example.aquatrack.feature_auth.presentation.utils.SignUpEvent
import com.example.aquatrack.feature_auth.presentation.utils.SignUpUiState
import com.example.aquatrack.util.checkEmailRegex
import com.example.aquatrack.util.passwordMatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState = _signUpUiState.asStateFlow()


    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _loginUiState.update {
                    it.copy(email = event.email)
                }
            }

            is LoginEvent.PasswordChanged -> {
                _loginUiState.update {
                    it.copy(password = event.password)
                }
            }

            is LoginEvent.TogglePasswordVisibility -> {
                _loginUiState.update {
                    it.copy(isPasswordVisible = event.isVisible)
                }
            }

            is LoginEvent.RememberMeChanged -> {
                _loginUiState.update {
                    it.copy(rememberMe = event.rememberMe)
                }
            }

            is LoginEvent.LoginButtonClicked -> {
                viewModelScope.launch {
                    _loginUiState.update { it.copy(isLoading = true) }

                    val result = authUseCases.signInUseCase(
                        email = _loginUiState.value.email,
                        password = _loginUiState.value.password
                    )
                    result.collect { data ->
                        if (data.data != null) _loginUiState.update { it.copy(isLoading = false) }
                    }

                }
            }

            is LoginEvent.GoogleSignInClicked -> {
                _loginUiState.update {
                    it.copy(isLoading = true)
                }


            }

            is LoginEvent.ForgotPasswordClicked -> {
                viewModelScope.launch {
                    _loginUiState.update { it.copy(isLoading = true) }
                    val result = authUseCases.forgotPasswordUseCase(
                        email = _loginUiState.value.email
                    )

                    if (result.data != null) _loginUiState.update {
                        it.copy(
                            isLoading = false,
                            forgotPassword = true
                        )
                    }
                }

            }
        }
    }

    fun onSignUpEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChanged -> {
                _signUpUiState.update {
                    it.copy(
                        email = event.email,
                        isEmailValid = event.email.checkEmailRegex()
                    )
                }
            }

            is SignUpEvent.PasswordChange -> {
                _signUpUiState.update {
                    it.copy(
                        password = event.password,
                        isPasswordMatch = passwordMatch(
                            event.password,
                            _signUpUiState.value.confirmPassword
                        )
                    )
                }
            }

            is SignUpEvent.ConfirmPasswordChange -> {
                _signUpUiState.update {
                    it.copy(
                        confirmPassword = event.confirmPassword,
                        isPasswordMatch = passwordMatch(
                            event.confirmPassword,
                            _signUpUiState.value.password
                        )
                    )
                }
            }

            is SignUpEvent.TogglePasswordVisibility -> {
                _signUpUiState.update {
                    it.copy(isPasswordVisible = event.isVisible)
                }
            }

            is SignUpEvent.ToggleConfirmPasswordVisibility -> {
                _signUpUiState.update {
                    it.copy(isConfirmPasswordVisible = event.isVisible)
                }
            }

            is SignUpEvent.SignUpClick -> {
                viewModelScope.launch {
                    _signUpUiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }

                    val result = authUseCases.signUpUseCase(
                        email = _signUpUiState.value.email,
                        password = _signUpUiState.value.password
                    )
                    result.collect { data ->
                        if (data.data != null) _signUpUiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }
                }
            }

            is SignUpEvent.GoogleClick -> {

            }

        }

    }

}