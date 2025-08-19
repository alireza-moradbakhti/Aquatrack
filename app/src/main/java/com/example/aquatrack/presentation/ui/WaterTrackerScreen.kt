package com.example.aquatrack.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.*
import com.example.aquatrack.R
import com.example.aquatrack.presentation.ui.components.GoalProgress
import com.example.aquatrack.presentation.ui.components.WaterGlassAnimation
import com.example.aquatrack.presentation.ui.components.WaterHistoryList
import com.example.aquatrack.presentation.util.WaterTrackerEvent
import com.example.aquatrack.presentation.viewmodel.WaterTrackerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterTrackerScreen(
    viewModel: WaterTrackerViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.water_loading))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = uiState.isAnimationPlaying,
        restartOnPlay = true,
        speed = 2.0f
    )

    // Send an event back to the ViewModel when the animation completes
    LaunchedEffect(lottieProgress) {
        if (lottieProgress == 1f) {
            viewModel.onEvent(WaterTrackerEvent.AnimationFinished)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.gothic_blue_light),
                    titleContentColor = colorResource(R.color.white)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GoalProgress(
                glassesCount = uiState.glassesCount,
                dailyGoal = uiState.dailyGoal
            )

            Spacer(modifier = Modifier.height(32.dp))

            WaterGlassAnimation(
                composition = lottieComposition,
                progress = { lottieProgress }
            ) {
                // Prevent clicks while animation is playing
                if (!uiState.isAnimationPlaying) {
                    viewModel.onEvent(WaterTrackerEvent.AddWaterClicked)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(stringResource(R.string.history_title), style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            WaterHistoryList(records = uiState.records)
        }
    }
}