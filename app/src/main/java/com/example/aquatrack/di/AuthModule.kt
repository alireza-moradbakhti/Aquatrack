package com.example.aquatrack.di

import com.example.aquatrack.feature_auth.data.repository.AuthRepositoryImpl
import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
import com.example.aquatrack.feature_auth.domain.usecase.AuthUseCases
import com.example.aquatrack.feature_auth.domain.usecase.ForgotPasswordUseCase
import com.example.aquatrack.feature_auth.domain.usecase.GetCurrentUserUseCase
import com.example.aquatrack.feature_auth.domain.usecase.SignInWithEmailAndPassUseCase
import com.example.aquatrack.feature_auth.domain.usecase.SignInWithGoogleUseCase
import com.example.aquatrack.feature_auth.domain.usecase.SignOutUseCase
import com.example.aquatrack.feature_auth.domain.usecase.SignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {


    /**
     * Provides a singleton instance of FirebaseAuth.
     * This is used for authentication operations in the application.
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Provides an instance of AuthRepository.
     * This is the implementation of the AuthRepository interface.
     */
    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            currentUserUseCase = GetCurrentUserUseCase(repository),
            signInUseCase = SignInWithEmailAndPassUseCase(repository),
            signInWithGoogle = SignInWithGoogleUseCase(repository),
            signOutUseCase = SignOutUseCase(repository),
            forgotPasswordUseCase = ForgotPasswordUseCase(repository),
            signUpUseCase = SignUpUseCase(repository)
        )
    }

}