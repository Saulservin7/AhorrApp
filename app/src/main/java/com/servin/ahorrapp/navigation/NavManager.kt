package com.servin.ahorrapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.servin.ahorrapp.view.games.Roulette
import com.servin.ahorrapp.view.home.HomeView
import com.servin.ahorrapp.view.onboarding.MainOnBoarding
import com.servin.ahorrapp.view.profile.Profile
import com.servin.ahorrapp.view.settings.Settings
import com.servin.ahorrapp.view.splashscreen.SplashScreen
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel
import com.servin.ahorrapp.viewmodel.RouletteViewModel

@Composable

fun NavManager(onBoardingViewModel: OnBoardingViewModel,rouletteViewModel: RouletteViewModel) {

    val navController = rememberNavController()
    val navigationItem = listOf(
        NavigationItem.Home,
        NavigationItem.Profile,
        NavigationItem.Settings,
        NavigationItem.Ruleta,
        NavigationItem.OnBoarding
    )
    val onBoardingState = onBoardingViewModel.onboardingState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = if (onBoardingState) "home" else "onboarding"
    ) {
        navigationItem.forEach { item ->
            composable(item.route) {
                when (item) {
                    NavigationItem.Home -> HomeView(navController,rouletteViewModel)
                    NavigationItem.Profile -> Profile(navController)
                    NavigationItem.Settings -> Settings(navController)
                    NavigationItem.Ruleta -> Roulette(navController,rouletteViewModel)
                    NavigationItem.OnBoarding -> MainOnBoarding(onBoardingViewModel, navController)
                }
            }

        }
    }

}