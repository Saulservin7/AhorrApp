package com.servin.ahorrapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreBoarding(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "onboarding")
        val STORE_BOARD = booleanPreferencesKey("store_board")
    }

    val getOnboarding: Flow<Boolean> = context.dataStore.data.map {
        it[STORE_BOARD] ?: false
    }

    suspend fun setOnboarding(value: Boolean) {
        context.dataStore.edit {
            it[STORE_BOARD] = value
        }
    }

}