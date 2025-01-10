package com.servin.ahorrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.servin.ahorrapp.ui.theme.AhorrAppTheme
import com.servin.ahorrapp.view.onboarding.MainOnBoarding
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val onBoardingViewModel: OnBoardingViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            AhorrAppTheme {
                MainOnBoarding(onBoardingViewModel)
            }
        }
    }
}

