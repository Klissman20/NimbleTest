package com.example.nimbletest.domain.repositories

interface DataStoreRepository {
    suspend fun setPreferenceValue(key: String, value: String)
    suspend fun getPreferenceValue(key: String): String?
    suspend fun clearPreferences(key:String)
}