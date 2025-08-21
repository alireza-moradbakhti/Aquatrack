package com.example.aquatrack.feature_auth.domain.repository

import com.example.aquatrack.util.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the authentication data layer.
 * This abstracts the underlying authentication provider (Firebase).
 */
interface AuthRepository {

    /**
     * Flow that emits the current authenticated user.
     * If no user is authenticated, it emits a null value.
     */
    val currentUser: Flow<FirebaseUser?>

    /**
     * Signs in a user with google.
     * @param idToken The ID token received from the Google Sign-In process.
     * This token is used to authenticate the user with Firebase.
     * @return A Resource containing the FirebaseUser if successful, or an error if not.
     */
    suspend fun signInWithGoogle(idToken: String): Resource<FirebaseUser>


    /**
     * Signs in a user with email and password.
     * @param email The user's email address.
     * @param password The user's password.
     * @return A Resource containing the FirebaseUser if successful, or an error if not.
     */
    suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<FirebaseUser>

    /**
     * Sign out a user.
     * @return A Resource indicating the success or failure of the sign-out operation.
     */
    suspend fun signOut(): Resource<Unit>

}