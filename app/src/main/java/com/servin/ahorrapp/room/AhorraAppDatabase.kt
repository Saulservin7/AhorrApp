package com.servin.ahorrapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.servin.ahorrapp.data.Converters
import com.servin.ahorrapp.model.Goals
import com.servin.ahorrapp.model.Rooms
import com.servin.ahorrapp.model.Saving
import com.servin.ahorrapp.model.Users


@Database(entities = [Users::class, Saving::class, Goals::class,Rooms::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AhorraAppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun savingDao(): SavingsDao
    abstract fun goalsDao(): GoalsDao
    abstract fun roomsDao(): RoomsDao

}