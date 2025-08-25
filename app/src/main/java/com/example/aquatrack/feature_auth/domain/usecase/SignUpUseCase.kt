package com.example.aquatrack.feature_auth.domain.usecase

import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
import com.example.aquatrack.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for sign up a user.
 *
 * This use case interacts with the AuthRepository to perform the signUp operation using an email and password.
 * and then emits the result of the signUp operation.
 */
class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    /**
     * This use case is responsible for Signing up a user with email and password.
     * It emits a loading state while the SingUp process is ongoing and then emits the result.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return A flow that emits the result of the SignUp operation.
     */
    operator fun invoke(email: String, password: String) = flow {
        emit(Resource.Loading())
        val result = repository.signUpWithEmailAndPassword(email, password)
        emit(result)
    }
}