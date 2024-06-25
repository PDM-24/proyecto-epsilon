package com.hnrylvo.inmomarket.ux.home

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
import com.hnrylvo.inmomarket.ux.property_view.PropertyViewRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    context: Context,
    private val navController: NavController
) : ViewModel() {

    private val propertyRepository = PropertyRepository()
    private val authPreferencesManager = AuthPreferencesManager(context = context)

    private val _propertyList = MutableStateFlow<List<GetAllPropertyResponse>>(emptyList())
    val propertyList = _propertyList.asStateFlow()

    val isLoading = mutableStateOf(false)



    fun navigateToDetail(id: String) {
        navController.navigate(
            PropertyViewRoute.createRoute(
                propertyId = id
            )
        )
    }

    fun getProperties() {
        isLoading.value = true
        viewModelScope.launch {
            authPreferencesManager.getAuthToken().collectLatest { token ->
                Log.d("HomeViewModel", "Token: $token")
                if (token != null) {
                    propertyRepository.getProperties(token).collectLatest { response ->
                        response.onSuccess { apiPropertyList ->
                            Log.d("HomeViewModel", "Properties: $apiPropertyList")

                            Log.d("HomeViewModel", "Updating property list")
                            _propertyList.update { apiPropertyList }
                            Log.d("HomeViewModel", "Property list updated: ${_propertyList.value}")
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