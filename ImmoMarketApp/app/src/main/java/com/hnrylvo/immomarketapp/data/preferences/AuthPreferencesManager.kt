package com.hnrylvo.inmomarket.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hnrylvo.inmomarket.data.utils.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferencesManager(private val context: Context) {

    // Define a key for the string value
    private val STRING_KEY = stringPreferencesKey("auth_token")

    // Function to save a string value to DataStore
    suspend fun saveAuthToken(value: String) {
        context.dataStore.edit { preferences ->
            preferences[STRING_KEY] = value
        }
    }

    // Function to get a string value from DataStore
    fun getAuthToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[STRING_KEY]
        }
    }

    suspend fun clearAuthToken() {
        context.dataStore.edit { preferences ->
            preferences[STRING_KEY] = ""
        }
    }
}
