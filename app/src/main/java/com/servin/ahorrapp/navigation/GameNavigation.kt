package com.servin.ahorrapp.navigation

sealed class GameNavigation (var route:String, var title: String) {

    object Ruleta: GameNavigation("ruleta", "ruleta")

}