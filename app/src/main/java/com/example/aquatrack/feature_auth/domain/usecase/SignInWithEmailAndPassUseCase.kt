package com.example.aquatrack.feature_auth.domain.usecase

import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
import com.example.aquatrack.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Use case for signing in a user with email and password.
 *
 * This use case interacts with the AuthRepository to perform the sign-in operation using email and password.
 * It does not currently implement any functionality but serves as a placeholder for future implementation.
 */
class SignInWithEmailAndPassUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * This use case is responsible for signing in a user with email and password.
     * It currently does not implement any functionality but serves as a placeholder for future implementation.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     */
    operator fun invoke(email: String, password: String) = flow {
        emit(Resource.Loading())
        val result = authRepository.signInWithEmailAndPassword(email, password)
        emit(result)
    }
}