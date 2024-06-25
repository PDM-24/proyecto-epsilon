package com.hnrylvo.immomarketapp.ux.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hnrylvo.inmomarket.data.networking.model.request.LoginRequest
import com.hnrylvo.inmomarket.data.preferences.AuthPreferencesManager
import com.hnrylvo.inmomarket.data.repository.AuthRepository
import com.hnrylvo.inmomarket.data.utils.onFailure
import com.hnrylvo.inmomarket.data.utils.onSuccess
import com.hnrylvo.inmomarket.utils.isEmailValid
import com.hnrylvo.inmomarket.utils.isPasswordValid
import com.hnrylvo.inmomarket.ux.home.HomeRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel (
    context: Context,
    private val navController: NavController
) : ViewModel() {

    private val authRepository = AuthRepository()
    private val authPreferencesManager = AuthPreferencesManager(context = context)

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginEnabled = MutableStateFlow(false)
    val loginEnabled = _loginEnabled.asStateFlow()

    private val _snackBarMessage = MutableStateFlow<String?>(null)
    val snackBarMessage = _snackBarMessage.asStateFlow()

    fun onValueChange(email : String, password : String){
        _email.update { email }
        _password.update { password }
        checkFormEnabled()
    }

    private fun checkFormEnabled() {
        val isFormEnabled = _email.value.isNotEmpty() &&
                _password.value.isNotEmpty() &&
                _email.value.isEmailValid() &&
                _password.value.isPasswordValid()
        _loginEnabled.value = isFormEnabled
    }

    fun login() {
        val request = LoginRequest(
            email = _email.value,
            password = _password.value
        )
        viewModelScope.launch {
            authRepository.login(request).collectLatest { response ->
                response.onSuccess {
                    authPreferencesManager.saveAuthToken(it.token)
                    _snackBarMessage.value = "Bienvenid@ ${_email.value}"
                    navigateToNextScreen()
                }
                response.onFailure {
                    _snackBarMessage.value = "Error al iniciar sesión"
                    Log.e("LoginViewModel", "login: ${it.throwable.message}")
                }
            }
        }
    }

    private fun navigateToNextScreen() {
        navController.navigate(HomeRoute.route)
    }

    fun clearSnackBarMessage() {
        _snackBarMessage.value = null
    }
}
