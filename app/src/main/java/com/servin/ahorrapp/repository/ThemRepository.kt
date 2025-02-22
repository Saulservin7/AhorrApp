package com.servin.ahorrapp.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.servin.ahorrapp.datastore.ThemePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val isDarkTheme: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[ThemePreferences.DARK_THEME] ?: false
    }

    suspend fun saveThemePreference(isDarkTheme:Boolean){
        dataStore.edit {
            preferences -> preferences[ThemePreferences.DARK_THEME] = isDarkTheme
        }
    }
}