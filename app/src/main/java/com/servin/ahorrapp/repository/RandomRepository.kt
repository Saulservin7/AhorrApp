package com.servin.ahorrapp.repository

import com.servin.ahorrapp.data.NetworkResult
import com.servin.ahorrapp.data.remote.RandomApiService
import com.servin.ahorrapp.model.RandomRequest
import javax.inject.Inject
import retrofit2.Response

class RandomRepository @Inject constructor(
    private val apiService: RandomApiService
) {
    suspend fun getRandomNumber(apiKey: String, min: Int, max: Int): NetworkResult<Int> {
        return try {
            val request = RandomRequest(
                params = RandomRequest.Params(
                    apiKey = apiKey,
                    n = 1,
                    min = min,
                    max = max
                )
            )
            val response = apiService.generateInteger(request)
            if (response.isSuccessful) {
                val data = response.body()?.result?.random?.data?.firstOrNull()
                if (data != null) {
                    NetworkResult.Success(data) // ✅ Usa NetworkResult.Success
                } else {
                    NetworkResult.Error(Exception("Datos vacíos")) // ✅ Usa NetworkResult.Error
                }
            } else {
                NetworkResult.Error(Exception("Error: ${response.code()}")) // ✅
            }
        } catch (e: Exception) {
            NetworkResult.Error(e) // ✅
        }
    }
}