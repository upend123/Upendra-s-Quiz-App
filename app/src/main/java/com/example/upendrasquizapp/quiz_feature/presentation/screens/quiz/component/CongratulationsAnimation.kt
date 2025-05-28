package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation


import com.airbnb.lottie.compose.LottieCompositionSpec

import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.noRippleClickable

import java.nio.file.WatchEvent


@Composable
fun CongratulationsAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congrg))
    var isPlaying by remember { mutableStateOf(true) }

    // Animate the Lottie composition with infinite iterations
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        restartOnPlay = true,  // Ensures the animation restarts when played again
       iterations = 50,
        reverseOnRepeat = false,
        speed = 1.5f
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .size(400.dp)
            .background(Color.Transparent)
            .noRippleClickable(onClick = { isPlaying = !isPlaying  })   // Toggle play/pause on click
    )
}
//
//@Composable
//fun CongratulationAnimation(
//    stateChangeCall: (Boolean) -> Unit
//){
//
//    var isVisible by remember { mutableStateOf(false) }
//
//    val alpha by animateFloatAsState(targetValue = if(isVisible) 1f else 1f,
//        animationSpec = tween(durationMillis = 500)
//    )
//
//
//    Box(modifier = Modifier.size(Dimens.LargeLottieAnimSize)
//        .alpha(alpha)
//        .background(Color.Red)
//        .clickable{  isVisible = !isVisible
//                      stateChangeCall(isVisible)}){
//
//
//           Icon( if(isVisible)  painterResource( R.drawable.ic_animated) else painterResource( R.drawable.baseline_egg_24), contentDescription = "congratulation_icon",
//               modifier = Modifier.size(Dimens.LargeLottieAnimSize))
//
//
//    }
//}
