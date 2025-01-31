package com.servin.ahorrapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.servin.ahorrapp.data.Game
import com.servin.ahorrapp.data.GameTypeAdapter
import com.servin.ahorrapp.model.Rooms
import com.servin.ahorrapp.repository.AhorraAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouletteViewModel @Inject constructor(
    private val repository: AhorraAppRepository,
    private val gson:Gson

) :
    ViewModel() {

    private val _initvalue = mutableStateOf("")
    val initvalue = _initvalue

    private val _finalvalue = mutableStateOf("")
    val finalvalue = _finalvalue

    private val showFields = mutableStateOf(true)
    val showFieldsState = showFields

    private val _roomsList = MutableStateFlow<List<Rooms>>(emptyList())
    val roomsList = _roomsList.asStateFlow()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRoomsByUserId(1).collect { item ->
                _roomsList.value = item.ifEmpty { emptyList() } // Usa .value
            }
        }
    }



    fun showFields() {
        showFields.value = false
    }


    fun setInitValue(value: String) {
        val intValue = value.toIntOrNull()
        if (intValue == null || intValue >= 0) {
            _initvalue.value = value
        }
    }

    fun setFinalValue(value: String) {
        _finalvalue.value = value
    }


    private val _rotationAngle = MutableStateFlow(0f)
    val rotationAngle = _rotationAngle.asStateFlow()

    fun startRotation() {
        // Simula una rotación aleatoria (por ejemplo, de 720 a 1080 grados)
        val randomRotation = (720..1080).random()
        _rotationAngle.update { it + randomRotation }
    }


    fun addRoom(rooms: Rooms) = viewModelScope.launch { repository.insertRoom(rooms)
        Log.d("RouletteViewModel", "Room inserted: $rooms") }

    suspend fun getRooms() = repository.getRoomsByUserId(1)


}