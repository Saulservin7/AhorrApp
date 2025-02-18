package com.servin.ahorrapp.data.remote

import com.servin.ahorrapp.model.RandomRequest
import com.servin.ahorrapp.model.RandomResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RandomApiService {
    @POST("json-rpc/4/invoke")
    suspend fun generateInteger(@Body randomRequest: RandomRequest): Response<RandomResponse>

}