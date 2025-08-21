package com.example.aquatrack.di

import com.example.aquatrack.feature_auth.data.repository.AuthRepositoryImpl
import com.example.aquatrack.feature_auth.domain.repository.AuthRepository
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

}