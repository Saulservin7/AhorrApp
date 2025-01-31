package com.servin.ahorrapp.repository

import com.servin.ahorrapp.model.Goals
import com.servin.ahorrapp.model.Rooms
import com.servin.ahorrapp.model.Saving
import com.servin.ahorrapp.model.Users
import com.servin.ahorrapp.room.AhorraAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AhorraAppRepository @Inject constructor(private val ahorraAppDatabase: AhorraAppDatabase) {

    suspend fun addUser(users: Users) {
        ahorraAppDatabase.userDao().insertUser(users)
    }
    suspend fun updateUser(users: Users) {
        ahorraAppDatabase.userDao().updateUser(users)
    }
    suspend fun deleteUser(users: Users) {
        ahorraAppDatabase.userDao().deleteUser(users)
    }
    suspend fun getUser(): Flow<List<Users>> =
        ahorraAppDatabase.userDao().getUser().flowOn(Dispatchers.IO).conflate()




    suspend fun getSaving(): Flow<List<Saving>> =
        ahorraAppDatabase.savingDao().getAllSavings().flowOn(Dispatchers.IO).conflate()
    suspend fun insertSaving(saving: Saving) = ahorraAppDatabase.savingDao().insertSaving(saving)
    suspend fun updateSaving(saving: Saving) = ahorraAppDatabase.savingDao().updateSaving(saving)



    suspend fun getGoals(): Flow<List<Goals>> =
        ahorraAppDatabase.goalsDao().getAllGoals().flowOn(Dispatchers.IO).conflate()
    suspend fun insertGoal(goal: Goals) = ahorraAppDatabase.goalsDao().insertGoal(goal)
    suspend fun updateGoal(goal: Goals) = ahorraAppDatabase.goalsDao().updateGoal(goal)
    suspend fun deleteGoal(goal: Goals) = ahorraAppDatabase.goalsDao().deleteGoal(goal)


    suspend fun getRoomsByUserId(userId: Int): Flow<List<Rooms>> =
        ahorraAppDatabase.roomsDao().getRoomsByUserId(userId).flowOn(Dispatchers.IO).conflate()
    suspend fun insertRoom(room: Rooms) = ahorraAppDatabase.roomsDao().insertRoom(room)
    suspend fun updateRoom(room: Rooms) = ahorraAppDatabase.roomsDao().updateRoom(room)
    suspend fun deleteRoom(room: Rooms) = ahorraAppDatabase.roomsDao().deleteRoom(room)


}