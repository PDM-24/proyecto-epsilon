package com.hnrylvo.inmomarket.data.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

// Create a DataStore instance
val Context.dataStore by preferencesDataStore(name = "authentication")
