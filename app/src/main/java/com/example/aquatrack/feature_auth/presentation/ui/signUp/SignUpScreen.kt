package com.example.aquatrack.feature_auth.presentation.ui.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.aquatrack.R
import com.example.aquatrack.feature_auth.presentation.utils.LoginEvent
import com.example.aquatrack.feature_auth.presentation.utils.SignUpEvent
import com.example.aquatrack.feature_auth.presentation.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    viewmodel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewmodel.signUpUiState.collectAsStateWithLifecycle()
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

            Text(
                text = stringResource(R.string.sign_up),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp)
            )

            // Email Field
            OutlinedTextField(
                value = uiState.email,
                onValueChange = {
                    viewmodel.onSignUpEvent(SignUpEvent.EmailChanged(it))
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

            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    viewmodel.onSignUpEvent(SignUpEvent.PasswordChange(it))
                },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null)
                },
                trailingIcon = {
                    val image = if (uiState.isPasswordVisible)
                        Icons.Default.Visibility
                    else Icons.Default.VisibilityOff

                    IconButton(onClick = { SignUpEvent.TogglePasswordVisibility(!uiState.isPasswordVisible) }) {
                        Icon(image, contentDescription = "password_visibility_icon")
                    }
                },
                placeholder = { Text(stringResource(R.string.password_placeholder)) },
                visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

        }
    }
}