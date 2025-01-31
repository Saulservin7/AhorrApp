package com.servin.ahorrapp.navigation

import android.icu.text.CaseMap.Title
import com.servin.ahorrapp.R

sealed class NavigationItem(var route: String, var icon: Int?=null, var title: String) {

    object Home : NavigationItem("home", R.drawable.home, "Home")
    object Profile : NavigationItem("profile", R.drawable.profile, "Profile")
    object Settings : NavigationItem("settings", R.drawable.settings, "Settings")
    object Ruleta : NavigationItem("Ruleta", title = "Ruleta")
    object OnBoarding : NavigationItem("onboarding", title = "OnBoarding")

}