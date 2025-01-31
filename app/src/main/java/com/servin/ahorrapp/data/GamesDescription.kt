package com.servin.ahorrapp.data

import com.servin.ahorrapp.R

sealed class GamesDescription (val name: String, val description: String, val image: Int) {
    object Ruleta: GamesDescription("Ruleta", "Gira la ruleta y ahorra ", R.drawable.ruleta)
    object Trivia: GamesDescription("Trivia", "Contesta preguntas y ahorra", R.drawable.ruleta)
}