package com.example.aquatrack.feature_auth.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aquatrack.R
import com.example.aquatrack.feature_auth.presentation.utils.LoginEvent
import com.example.aquatrack.feature_auth.presentation.viewmodel.AuthViewModel
import com.example.aquatrack.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.primary_light_blue)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = colorResource(R.color.gray_shade1))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = stringResource(R.string.sign_in),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp)
            )

            // Email Field
            OutlinedTextField(
                value = uiState.email,
                onValueChange = {
                    viewModel.onLoginEvent(LoginEvent.EmailChanged(it))
                },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "email_icon")
                },
                placeholder = { Text(stringResource(R.string.email_placeholder)) },
                colors = TextFieldDefaults.colors(
                    cursorColor = colorResource(R.color.primary_green),
                    focusedTextColor = colorResource(R.color.primary_green),
                    focusedLeadingIconColor = colorResource(R.color.primary_green),
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password Field
            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    viewModel.onLoginEvent(LoginEvent.PasswordChanged(it))
                },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null)
                },
                trailingIcon = {
                    val image = if (uiState.isPasswordVisible)
                        Icons.Default.Visibility
                    else Icons.Default.VisibilityOff

                    IconButton(onClick = { LoginEvent.TogglePasswordVisibility(!uiState.isPasswordVisible) }) {
                        Icon(image, contentDescription = "password_visibility_icon")
                    }
                },
                placeholder = { Text(stringResource(R.string.password_placeholder)) },
                visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Remember Me + Forgot Password Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = uiState.rememberMe, onCheckedChange = {
                        viewModel.onLoginEvent(LoginEvent.RememberMeChanged(it))
                    })
                    Text(stringResource(R.string.remember_me))
                }

                Text(
                    text = stringResource(R.string.forgot_password),
                    color = colorResource(R.color.primary_green),
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.ForgotPassword.route)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            Button(
                onClick = {
                    viewModel.onLoginEvent(LoginEvent.LoginButtonClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary_green),
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.login), color = colorResource(R.color.white))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Continue with Google
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.continue_with_google))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up Text
            Row {
                Text(stringResource(R.string.no_account))
                Text(
                    text = stringResource(R.string.sign_up),
                    color = colorResource(R.color.primary_green),
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.SignUp.route) {
                            popUpTo(Screen.Welcome.route)
                        }
                    }
                )
            }
        }
    }

    //todo: handle if user pressed back to close the app
    if (!uiState.isLoading) navController.navigate(Screen.Home.route)
}