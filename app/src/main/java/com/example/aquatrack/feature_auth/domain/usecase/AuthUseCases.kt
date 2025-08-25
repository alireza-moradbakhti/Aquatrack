package com.example.aquatrack.feature_auth.domain.usecase

data class AuthUseCases (
    val currentUserUseCase: GetCurrentUserUseCase,
    val signInUseCase: SignInWithEmailAndPassUseCase,
    val signInWithGoogle : SignInWithGoogleUseCase,
    val signOutUseCase: SignOutUseCase,
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val signUpUseCase: SignUpUseCase

)