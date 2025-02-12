package com.servin.ahorrapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomApiService {
    @GET("integers/")
    suspend fun getRandomNumber(
        @Query("num") count: Int = 1,
        @Query("min") min: Int,
        @Query("max") max: Int,
        @Query("col") col: Int = 1,
        @Query("base") base: Int = 10,
        @Query("format") format: String = "plain",
        @Query("rnd") rnd: String = "new"
    ): String // Retorna un String (el número en texto plano)
}