package com.example.aquatrack.feature_home.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation

@Composable
fun WaterGlassAnimation(
    composition: LottieComposition?,
    progress: () -> Float,
    onGlassClick: () -> Unit
) {
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier
            .size(200.dp)
            .clickable(onClick = onGlassClick)
    )
}