package com.servin.ahorrapp.view.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.servin.ahorrapp.R
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel


@Composable

fun LoaderData(modifier: Modifier, image: Int, onBoardingViewModel: OnBoardingViewModel) {

    val compositionSpec by onBoardingViewModel.composition.collectAsState()
    val composition by rememberLottieComposition(compositionSpec)

    LaunchedEffect(Unit) { onBoardingViewModel.setImage(image) }


    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}