package com.example.aquatrack.feature_auth.domain.usecase

import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String) = repository.forgotPassword(email)

}