package com.servin.ahorrapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.servin.ahorrapp.model.Users
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM usuarios")
    fun getUser(): Flow<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: Users)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: Users)

    @Delete
    suspend fun deleteUser(user: Users)


}