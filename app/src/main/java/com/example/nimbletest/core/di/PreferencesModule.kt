package com.example.nimbletest.core.di

import android.content.Context
import com.example.nimbletest.infrastructure.datasources.datastore.Preferences
import com.example.nimbletest.infrastructure.datasources.datastore.PreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext appContext: Context): Preferences =
        PreferencesImpl(appContext)

}