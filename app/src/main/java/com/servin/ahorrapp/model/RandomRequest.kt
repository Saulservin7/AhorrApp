package com.servin.ahorrapp.model

data class RandomRequest(
    val jsonrpc: String = "2.0",
    val method: String = "generateIntegers",
    val params: Params,
    val id: Int = 1
) {

    data class Params(
        val apiKey: String,
        val n: Int = 1,
        val min: Int,
        val max: Int,
        val replacement: Boolean = true,
        val base: Int = 10
    )
}