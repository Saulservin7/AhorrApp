package com.servin.ahorrapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.servin.ahorrapp.navigation.NavManager
import com.servin.ahorrapp.ui.theme.AhorrAppTheme
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel
import com.servin.ahorrapp.viewmodel.RouletteViewModel
import com.servin.ahorrapp.viewmodel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        val onBoardingViewModel: OnBoardingViewModel by viewModels()
        val rouletteViewModel: RouletteViewModel by viewModels()


        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            var isReady by remember { mutableStateOf(false) }
            /* var isDarkMode by remember { mutableStateOf(false) } // Estado para el tema*/
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDarkTheme by themeViewModel.darkThemeState.collectAsState()
            LaunchedEffect(Unit) {
                delay(300) // Simulación de carga
                isReady = true
            }

            splashScreen.setKeepOnScreenCondition { !isReady }

            AhorrAppTheme(darkTheme = isDarkTheme) {
                NavManager(
                    onBoardingViewModel = onBoardingViewModel,
                    rouletteViewModel = rouletteViewModel,
                    onThemeChange = { enabled ->
                        themeViewModel.toggleTheme(enabled)
                    }
                )
            }
        }
    }
}