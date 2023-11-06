package com.example.nimbletest.infrastructure.repositories

import com.example.nimbletest.domain.repositories.DataStoreRepository
import com.example.nimbletest.infrastructure.datastore.Preferences
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val preferences: Preferences
): DataStoreRepository {
    override suspend fun setPreferenceValue(key: String, value: String) {
        preferences.setPreferenceValue(key, value)
    }

    override suspend fun getPreferenceValue(key: String): String? {
        return preferences.getPreferenceValue(key)
    }

    override suspend fun clearPreferences(key: String) {
        preferences.clearPreferences(key)
    }
}