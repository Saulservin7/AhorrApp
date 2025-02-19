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

    @Query("""
        UPDATE rooms 
        SET game = json_replace(game, '$.usedNumbers', :newUsedNumbers) 
        WHERE id = :id
    """)
    suspend fun updateRuletaUsedNumbers(id: Int, newUsedNumbers: String)


    @Insert
    suspend fun insertRoom(room: Rooms)

    @Update
    suspend fun updateRoom(room: Rooms)

    @Delete
    suspend fun deleteRoom(room: Rooms)


}