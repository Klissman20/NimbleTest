package com.example.nimbletest.infrastructure.datastore

interface Preferences {
    suspend fun setPreferenceValue(key: String, value: String)
    suspend fun getPreferenceValue(key: String): String?
    suspend fun clearPreferences(key: String)
}