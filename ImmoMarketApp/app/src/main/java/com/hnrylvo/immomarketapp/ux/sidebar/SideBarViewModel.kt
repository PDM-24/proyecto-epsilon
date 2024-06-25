package com.hnrylvo.inmomarket.ux.sidebar

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hnrylvo.inmomarket.data.preferences.AuthPreferencesManager
import kotlinx.coroutines.launch

class SideBarViewModel(
    context: Context
) : ViewModel() {
    private val authPreferencesManager = AuthPreferencesManager(context = context)

//    fun logOut() {
//        viewModelScope.launch {
//            authPreferencesManager.clearAuthToken()
//        }
//    }
}