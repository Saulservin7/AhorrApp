package com.servin.ahorrapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson
import com.servin.ahorrapp.data.NetworkResult
import com.servin.ahorrapp.model.RandomResponse
import com.servin.ahorrapp.model.Rooms
import com.servin.ahorrapp.repository.AhorraAppRepository
import com.servin.ahorrapp.repository.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class RouletteViewModel @Inject constructor(
    private val repository: AhorraAppRepository,
    private val gson: Gson,
    private val randomRepository: RandomRepository

) :
    ViewModel() {


    private val _randomNumber = MutableLiveData<NetworkResult<Int>>()
    val randomNumber: LiveData<NetworkResult<Int>> = _randomNumber

    private val _initvalue = mutableStateOf("")
    val initvalue = _initvalue

    private val _finalvalue = mutableStateOf("")
    val finalvalue = _finalvalue


    private val _roomsList = MutableStateFlow<List<Rooms>>(emptyList())
    val roomsList = _roomsList.asStateFlow()

    private val _newRoomId = MutableStateFlow<Int?>(null)
    val newRoomId: StateFlow<Int?> = _newRoomId.asStateFlow()

    private val _room = MutableStateFlow<Rooms?>(null)
    val room = _room.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRoomsByUserId(1).collect { item ->
                _roomsList.value = item.ifEmpty { emptyList() } // Usa .value
            }
        }
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

    fun clearNewRoomId() {
        _newRoomId.value = null
    }

    fun clearRoom() {
        _room.value = null
    }

    fun addRoom(rooms: Rooms) = viewModelScope.launch {
        repository.insertRoom(rooms)

        repository.getRoomsByUserId(1).collect { rooms ->
            val lastRoom = rooms.maxByOrNull { it.id } // Asume que Rooms tiene un campo "id"
            _newRoomId.value = lastRoom?.id
            Log.d("RouletteVM", "Nuevo ID detectado: ${lastRoom?.id}")
        }

    }


    private suspend fun updateTotalSaving(id: Int, totalSaving: Long) {
        withContext(Dispatchers.IO) {
            repository.updateTotalSaving(id, totalSaving)
        }
    }
    fun getRoomById(id: Int) = viewModelScope.launch {
        val room = repository.getRoomById(id)
        _room.value = room
        Log.d("RouletteViewModel", "getRoomById: $room")
    }


    fun fetchRandomNumber(id: Int, totalSaving: Long, apiKey: String, min: Int, max: Int) {
        viewModelScope.launch {

            _randomNumber.value = NetworkResult.Loading
            val result = randomRepository.getRandomNumber(apiKey, min, max)
            _randomNumber.value = result

            val randomNumberValue = (result as? NetworkResult.Success)?.data ?: 0
            withContext(Dispatchers.IO) {
                repository.updateTotalSaving(id, totalSaving + randomNumberValue)
            }

            /*withContext(Dispatchers.IO) {
            repository.addNumberToRuleta(id, randomNumberValue)
            }*/

            // Llama a getRoomById para obtener los datos más recientes de la sala
            getRoomById(id)
        }
    }





}