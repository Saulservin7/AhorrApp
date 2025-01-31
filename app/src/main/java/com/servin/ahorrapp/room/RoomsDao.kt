package com.servin.ahorrapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.servin.ahorrapp.model.Rooms
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomsDao {

    @Query("SELECT * FROM rooms")
    fun getAllRooms(): Flow<List<Rooms>>

    @Query("SELECT * FROM rooms WHERE userId = :userId")
    fun getRoomsByUserId(userId: Int): Flow<List<Rooms>>

    @Insert
    suspend fun insertRoom(room: Rooms)

    @Update
    suspend fun updateRoom(room: Rooms)

    @Delete
    suspend fun deleteRoom(room: Rooms)
}