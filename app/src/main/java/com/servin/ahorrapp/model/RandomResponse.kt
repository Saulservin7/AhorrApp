package com.servin.ahorrapp.model

data class RandomResponse(
    val jsonrpc: String,
    val result: Result?,
    val id: Int
) {
    data class Result(
        val random: RandomData,
        val bitsUsed: Int,
        val bitsLeft: Int,
        val requestsLeft: Int,
        val advisoryDelay: Int
    )

    data class RandomData(
        val data: List<Int>,
        val completionTime: String
    )
}
