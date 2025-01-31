package com.servin.ahorrapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.servin.ahorrapp.model.Saving
import kotlinx.coroutines.flow.Flow


@Dao
interface SavingsDao {

    @Query("SELECT * FROM saving")
    fun getAllSavings(): Flow<List<Saving>>

    @Insert
    suspend fun insertSaving(saving: Saving)

    @Delete
    suspend fun updateSaving(saving: Saving)
}