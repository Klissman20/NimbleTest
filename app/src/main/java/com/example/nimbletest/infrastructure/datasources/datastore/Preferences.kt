package com.example.nimbletest.infrastructure.datasources.datastore

interface Preferences {
    suspend fun setTokenValue(key: String, value: String)
    suspend fun getTokenValue(key: String): String?
}