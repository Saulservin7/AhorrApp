package com.servin.ahorrapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.servin.ahorrapp.data.Game
import com.servin.ahorrapp.data.GameTypeAdapter
import com.servin.ahorrapp.datastore.StoreBoarding
import com.servin.ahorrapp.room.AhorraAppDatabase
import com.servin.ahorrapp.room.GoalsDao
import com.servin.ahorrapp.room.RoomsDao
import com.servin.ahorrapp.room.SavingsDao
import com.servin.ahorrapp.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideStoreBoarding(@ApplicationContext context: Context): StoreBoarding {
        return StoreBoarding(context)
    }

    @Provides
    @Singleton
    fun providesUsersDao(ahorraAppDatabase: AhorraAppDatabase):UserDao{
        return ahorraAppDatabase.userDao()
    }

    @Provides
    @Singleton
    fun providesSavingsDao(ahorraAppDatabase: AhorraAppDatabase): SavingsDao {
        return ahorraAppDatabase.savingDao()
    }

    @Provides
    @Singleton
    fun providesGoalsDao(ahorraAppDatabase: AhorraAppDatabase): GoalsDao {
        return ahorraAppDatabase.goalsDao()
    }

    @Provides
    @Singleton
    fun providesRoomsDao(ahorraAppDatabase: AhorraAppDatabase): RoomsDao {
        return ahorraAppDatabase.roomsDao()
    }


    @Provides
    @Singleton

    fun providesDatabase(@ApplicationContext context: Context): AhorraAppDatabase {
        return Room.databaseBuilder(
            context,
            AhorraAppDatabase::class.java, name = "ahorroapp_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Game::class.java, GameTypeAdapter())
            .create()
    }


}