package com.example.aquatrack.feature_auth.domain.usecase

import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
import com.example.aquatrack.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for signing in a user with Google.
 *
 * This use case interacts with the AuthRepository to perform the sign-in operation using an ID token
 * obtained from Google authentication. It emits a loading state while the sign-in is in progress
 * and then emits the result of the sign-in operation.
 */
class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    /**
     * This use case is responsible for signing in a user with Google using the provided ID token.
     * It emits a loading state while the sign-in process is ongoing and then emits the result.
     *
     * @param idToken The ID token received from Google after authentication.
     * @return A flow that emits the result of the sign-in operation.
     */
    operator fun invoke(idToken: String) = flow {
        emit(Resource.Loading())
        val result = authRepository.signInWithGoogle(idToken)
        emit(result)
    }
}