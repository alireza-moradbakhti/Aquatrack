package com.example.aquatrack.feature_auth.presentation.ui.welcome

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.aquatrack.R
import com.example.aquatrack.navigation.Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.primary_light_blue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_water),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = colorResource(R.color.primary_green),
                        shape = RoundedCornerShape(64.dp)
                    )
                    .padding(12.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.welcome),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black_shade10)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.welcome_message),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = colorResource(R.color.gray_shade7)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    containerColor = colorResource(R.color.primary_green),
                    contentColor = colorResource(R.color.white),
                    disabledContainerColor = colorResource(R.color.gray_shade7),
                    disabledContentColor = colorResource(R.color.gray_shade4)
                )
            ) {
                Text(
                    text = stringResource(R.string.btn_continue),
                    fontSize = 16.sp
                )
            }
        }
    }
}