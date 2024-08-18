package com.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.android.ext.koin.androidApplication
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.dsl.module

val datastoreModule = module {
    provideDataStorePref()
}

internal fun Module.provideDataStorePref(): KoinDefinition<DataStore<Preferences>> = single {
    PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceFile = {
            androidApplication().preferencesDataStoreFile("preferences_data_store")
        }
    )
}
