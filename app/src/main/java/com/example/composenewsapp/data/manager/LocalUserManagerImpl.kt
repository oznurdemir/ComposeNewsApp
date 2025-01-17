package com.example.composenewsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.composenewsapp.domain.manager.LocalUserManager
import com.example.composenewsapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context): LocalUserManager {
    override suspend fun saveAppEntry() {
        context.datastore.edit {settings->
            settings[PreferencesKeys.APP_ENTRY] = true

        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map {preferences->
            preferences[PreferencesKeys.APP_ENTRY]?:false
        }
    }
}

private val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}