package com.servin.ahorrapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.servin.ahorrapp.view.home.HomeView
import com.servin.ahorrapp.view.onboarding.MainOnBoarding
import com.servin.ahorrapp.view.splashscreen.SplashScreen
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel

@Composable

fun NavManager(onBoardingViewModel: OnBoardingViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("onboarding"){
            MainOnBoarding(onBoardingViewModel,navController)
        }
        composable("home") {
            HomeView()
        }
        composable("splash") {
            SplashScreen(navController,onBoardingViewModel)
        }

    }

}