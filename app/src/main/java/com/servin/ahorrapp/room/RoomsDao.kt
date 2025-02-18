package com.servin.ahorrapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.servin.ahorrapp.data.Game
import com.servin.ahorrapp.model.Rooms
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface RoomsDao {

    @Query("SELECT * FROM rooms")
    fun getAllRooms(): Flow<List<Rooms>>

    @Query("SELECT * FROM rooms ORDER BY id DESC LIMIT 1")
    fun getLastRoom(): Flow<Rooms>

    @Query("SELECT * FROM rooms WHERE userId = :userId")
    fun getRoomsByUserId(userId: Int): Flow<List<Rooms>>

    @Query("SELECT * FROM rooms WHERE id=:id")
    fun getRoomById(id: Int): Flow<Rooms>

    @Query("UPDATE rooms SET totalSaving=:totalSaving WHERE id=:id")
    suspend fun updateTotalSaving(id: Int, totalSaving: Long)



    @Insert
    suspend fun insertRoom(room: Rooms)

    @Update
    suspend fun updateRoom(room: Rooms)

    @Delete
    suspend fun deleteRoom(room: Rooms)


   /* suspend fun addNumberToRuleta(roomId: Int, newNumber: Int) {
        val room = getRoomById(roomId).first()
        when (val game = room.game) {
            is Game.Ruleta -> {
                // Si numbersList es null, usa una lista vacía
                val currentNumbers = game.numbersList ?: emptyList()
                val updatedNumbers = currentNumbers + newNumber
                val updatedGame = game.copy(numbersList = updatedNumbers)
                updateRoom(room.copy(game = updatedGame))
            }
            else -> throw Exception("Not a Ruleta game")
        }
    } */

}