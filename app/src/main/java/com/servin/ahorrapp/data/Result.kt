package com.servin.ahorrapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>() // Éxito (con datos)
    data class Error(val exception: Exception) : NetworkResult<Nothing>() // Error (con mensaje)
    object Loading : NetworkResult<Nothing>() // Estado de carga (sin datos)
}