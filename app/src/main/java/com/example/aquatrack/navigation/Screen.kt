package com.example.aquatrack.navigation

import com.example.aquatrack.util.AppConstants

/**
 * Represents the different screens in the AquaTrack application.
 *
 * Each screen corresponds to a specific route in the app's navigation graph.
 */
sealed class Screen(val route: String) {

    // Auth Flow
    data object Welcome : Screen(AppConstants.WELCOME_ROUTE)
    data object Login : Screen(AppConstants.LOGIN_ROUTE)
    data object SignUp : Screen(AppConstants.SIGNUP_ROUTE)
    data object ForgotPassword : Screen(AppConstants.FORGOT_PASSWORD_ROUTE)

    // Main App Flow
    data object Home : Screen(AppConstants.HOME_ROUTE)
    data object Awards : Screen(AppConstants.AWARDS_ROUTE)
    data object Profile : Screen(AppConstants.PROFILE_ROUTE)


}