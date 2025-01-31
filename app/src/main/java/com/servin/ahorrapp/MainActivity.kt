package com.servin.ahorrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.servin.ahorrapp.navigation.NavManager
import com.servin.ahorrapp.ui.theme.AhorrAppTheme
import com.servin.ahorrapp.view.onboarding.MainOnBoarding
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel
import com.servin.ahorrapp.viewmodel.RouletteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        val onBoardingViewModel: OnBoardingViewModel by viewModels()
        val rouletteViewModel: RouletteViewModel by viewModels()

       // splashScreen.setKeepOnScreenCondition()


        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            var isReady by remember { mutableStateOf(false) }
            AhorrAppTheme {
                LaunchedEffect(Unit) {
                    delay(300)
                    isReady = true
                }
                splashScreen.setKeepOnScreenCondition{!isReady}
                NavManager(onBoardingViewModel,rouletteViewModel)

            }

        }
    }
}



