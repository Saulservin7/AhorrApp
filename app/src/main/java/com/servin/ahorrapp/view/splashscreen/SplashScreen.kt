package com.servin.ahorrapp.view.splashscreen

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servin.ahorrapp.R
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController,onBoardingViewModel: OnBoardingViewModel) {
    Splash(navController,onBoardingViewModel)
}

@Composable
fun Splash(navController: NavController,onBoardingViewModel: OnBoardingViewModel) {
    val onBoardingState = onBoardingViewModel.onboardingState.collectAsState().value

    LaunchedEffect(Unit) {
        delay(500)
        if (onBoardingState) {
            navController.navigate("home")
        } else {
            navController.navigate("onboarding")
        }

    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "AhorrApp",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }

}


