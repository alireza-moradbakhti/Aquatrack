package com.example.aquatrack.feature_auth.domain.usecase

import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
import com.example.aquatrack.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for signing out a user.
 *
 * This use case interacts with the AuthRepository to perform the sign-out operation.
 * It emits a loading state while the sign-out is in progress and then emits the result of the operation.
 */
class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    /**
     * This use case is responsible for signing out the user.
     * It interacts with the AuthRepository to perform the sign-out operation.
     */
    operator fun invoke() = flow {
        emit(Resource.Loading())
        val result = authRepository.signOut()
        emit(result)
    }

}