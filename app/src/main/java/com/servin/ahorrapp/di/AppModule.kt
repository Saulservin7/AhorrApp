package com.servin.ahorrapp.di

import android.content.Context
import com.servin.ahorrapp.datastore.StoreBoarding
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


}