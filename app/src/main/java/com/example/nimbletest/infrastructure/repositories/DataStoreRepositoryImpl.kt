package com.example.nimbletest.infrastructure.repositories

import com.example.nimbletest.domain.repositories.DataStoreRepository
import com.example.nimbletest.infrastructure.datasources.datastore.Preferences
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val preferences: Preferences
): DataStoreRepository {
    override suspend fun setTokenValue(key: String, value: String) {
        preferences.setTokenValue(key, value)
    }

    override suspend fun getTokenValue(key: String): String? {
        return preferences.getTokenValue(key)
    }
}