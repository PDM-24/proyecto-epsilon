package com.hnrylvo.inmomarket.ux.property_view

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hnrylvo.inmomarket.data.networking.model.response.GetAllPropertyResponse
import com.hnrylvo.inmomarket.data.preferences.AuthPreferencesManager
import com.hnrylvo.inmomarket.data.repository.PropertyRepository
import com.hnrylvo.inmomarket.data.utils.onFailure
import com.hnrylvo.inmomarket.data.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PropertyViewModel(
    context: Context,
    private val navController: NavController
) : ViewModel() {

    private val propertyRepository = PropertyRepository()
    private val authPreferencesManager = AuthPreferencesManager(context = context)

    private val _propertyInfo = MutableStateFlow<GetAllPropertyResponse?>(null)
    val propertyInfo = _propertyInfo.asStateFlow()

    val isLoading = mutableStateOf(false)

    fun getPropertyById(propertyId: String) {
        isLoading.value = true
        viewModelScope.launch {
            authPreferencesManager.getAuthToken().collectLatest { token ->
                Log.d("HomeViewModel", "Token: $token")
                if (token != null) {
                    propertyRepository.getProperty(token, propertyId).collectLatest { response ->
                        response.onSuccess { property ->
                            Log.d("HomeViewModel", "Properties: $property")

                            Log.d("HomeViewModel", "Updating property list")
                            _propertyInfo.update { property }
                        }
                        response.onFailure {
                            Log.d("HomeViewModel", "Error: ${it.throwable.message}")
                        }
                    }
                } else {
                    Log.d("HomeViewModel", "No token found")
                }
                isLoading.value = false
            }
        }
    }
}