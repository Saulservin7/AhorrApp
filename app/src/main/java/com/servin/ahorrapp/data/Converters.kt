package com.servin.ahorrapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory

class Converters {
    // Configura el adaptador para la clase sellada
    private val gameAdapter = RuntimeTypeAdapterFactory.of(Game::class.java, "type")
        .registerSubtype(Game.Ruleta::class.java, "ruleta")
        .registerSubtype(Game.Trivia::class.java, "trivia")

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapterFactory(gameAdapter)
        .create()

    @TypeConverter
    fun fromGame(game: Game): String {
        return gson.toJson(game)
    }

    @TypeConverter
    fun toGame(gameString: String): Game {
        return gson.fromJson(gameString, Game::class.java)
    }
}