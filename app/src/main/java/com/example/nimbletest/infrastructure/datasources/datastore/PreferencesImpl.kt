package com.example.nimbletest.infrastructure.datasources.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val USER_PREFERENCES = "user_preferences"

private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES)

class PreferencesImpl @Inject constructor(
    private val context: Context
): Preferences {
    override suspend fun setTokenValue(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value

        }
    }

    override suspend fun getTokenValue(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}