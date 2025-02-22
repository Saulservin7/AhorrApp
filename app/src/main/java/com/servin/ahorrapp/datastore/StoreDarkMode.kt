package com.servin.ahorrapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

// Crea un archivo nuevo DataStoreModule.kt
val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

object ThemePreferences {
    val DARK_THEME = booleanPreferencesKey("dark_theme")
}