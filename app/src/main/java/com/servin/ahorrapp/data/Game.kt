package com.servin.ahorrapp.data

sealed class Game {
    data class Ruleta(val rangeStart: Int, val rangeEnd: Int,val type: String = "ruleta") : Game()
    data class Trivia(val totalQuestions: Int,val type: String = "trivia") : Game()
    // Agrega más tipos de juegos según sea necesario
}