package com.example.aquatrack.feature_auth.domain.usecase

import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Use case for retrieving the current user from the authentication repository.
 *
 * This use case is part of the domain layer and is responsible for accessing
 * the current user's information, which is typically stored in the authentication repository.
 */
class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    /**
     * This use case retrieves the current user from the authentication repository.
     * It is used to access the currently authenticated user's information.
     *
     * @return The current user, or null if no user is authenticated.
     */
    operator fun invoke() = authRepository.currentUser
}