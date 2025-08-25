package com.example.aquatrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aquatrack.feature_auth.presentation.ui.forgot_password.ForgetPasswordScreen
import com.example.aquatrack.feature_auth.presentation.ui.login.LoginScreen
import com.example.aquatrack.feature_auth.presentation.ui.signUp.SignUpScreen
import com.example.aquatrack.feature_auth.presentation.ui.welcome.WelcomeScreen

/**
 * Root navigation graph for the AquaTrack application.
 * This is the entry point for the navigation system.
 */
@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Welcome.route){
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = Screen.ForgotPassword.route) {
            ForgetPasswordScreen(navController = navController)
        }

        //todo: the main app screens will be added here
    }

}