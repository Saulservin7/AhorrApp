package com.servin.ahorrapp.data

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class GameTypeAdapter : TypeAdapter<Game>() {
    override fun write(out: JsonWriter, value: Game) {
        when (value) {
            is Game.Ruleta -> {
                out.beginObject()
                out.name("type").value("Ruleta")
                out.name("rangeStart").value(value.rangeStart)
                out.name("rangeEnd").value(value.rangeEnd)
                out.endObject()
            }
            is Game.Trivia -> {
                out.beginObject()
                out.name("type").value("Trivia")
                out.name("totalQuestions").value(value.totalQuestions)
                out.endObject()
            }
        }
    }

    override fun read(`in`: JsonReader): Game {
        var type: String? = null
        var rangeStart = 0
        var rangeEnd = 0
        var totalQuestions = 0

        `in`.beginObject()
        while (`in`.hasNext()) {
            when (val name = `in`.nextName()) {
                "type" -> type = `in`.nextString()
                "rangeStart" -> rangeStart = `in`.nextInt()
                "rangeEnd" -> rangeEnd = `in`.nextInt()
                "totalQuestions" -> totalQuestions = `in`.nextInt()
            }
        }
        `in`.endObject()

        return when (type) {
            "Ruleta" -> Game.Ruleta(rangeStart, rangeEnd)
            "Trivia" -> Game.Trivia(totalQuestions)
            else -> throw IllegalArgumentException("Unknown type: $type")
        }
    }
}