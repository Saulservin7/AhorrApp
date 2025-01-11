package com.servin.ahorrapp.view.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel


@Composable

fun LoaderData(modifier: Modifier, image: Int, onBoardingViewModel: OnBoardingViewModel) {

    val compositionSpec = LottieCompositionSpec.RawRes(image)
    val composition by rememberLottieComposition(compositionSpec)




    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}