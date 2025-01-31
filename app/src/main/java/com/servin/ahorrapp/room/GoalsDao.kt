package com.servin.ahorrapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.servin.ahorrapp.model.Goals
import kotlinx.coroutines.flow.Flow


@Dao
interface GoalsDao {

    @Query("SELECT * FROM goals")
    fun getAllGoals(): Flow<List<Goals>>

    @Insert
    suspend fun insertGoal(goal: Goals)

    @Update
    suspend fun updateGoal(goal: Goals)

    @Delete
    suspend fun deleteGoal(goal: Goals)

}