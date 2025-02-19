package com.servin.ahorrapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.servin.ahorrapp.view.games.roulette.Roulette
import com.servin.ahorrapp.view.home.HomeView
import com.servin.ahorrapp.view.onboarding.MainOnBoarding
import com.servin.ahorrapp.view.profile.Profile
import com.servin.ahorrapp.view.settings.Settings
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel
import com.servin.ahorrapp.viewmodel.RouletteViewModel

@Composable

fun NavManager(
    onBoardingViewModel: OnBoardingViewModel,
    rouletteViewModel: RouletteViewModel,
    isDarkMode: Boolean,
    onThemeChange: (Boolean) -> Unit
) {

    val navController = rememberNavController()
    val navigationItem = listOf(
        NavigationItem.Home,
        NavigationItem.Profile,
        NavigationItem.Settings,
        NavigationItem.OnBoarding
    )
    val onBoardingState = onBoardingViewModel.onboardingState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = if (onBoardingState) "home" else "onboarding"
    ) {

        composable(
            route = "${NavigationItem.Ruleta.route}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType // Usa StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val idString = backStackEntry.arguments?.getString("id")
            val id = idString?.toIntOrNull() // Convierte a Int? manualmente
            Roulette(navController, rouletteViewModel, id)
        }
        navigationItem.forEach { item ->
            composable(item.route) {
                when (item) {
                    NavigationItem.Home -> HomeView(navController, rouletteViewModel)
                    NavigationItem.Profile -> Profile(navController)
                    NavigationItem.Settings -> Settings(navController,isDarkMode,onThemeChange)
                    NavigationItem.Ruleta -> Roulette(navController, rouletteViewModel)
                    NavigationItem.OnBoarding -> MainOnBoarding(onBoardingViewModel, navController)
                }
            }

        }
    }

}