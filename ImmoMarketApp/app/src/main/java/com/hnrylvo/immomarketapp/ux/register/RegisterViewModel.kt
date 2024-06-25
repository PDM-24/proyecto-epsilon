package com.hnrylvo.inmomarket.ux.register


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hnrylvo.inmomarket.data.networking.model.request.RegisterRequest
import com.hnrylvo.inmomarket.data.repository.AuthRepository
import com.hnrylvo.inmomarket.data.utils.onFailure
import com.hnrylvo.inmomarket.data.utils.onSuccess
import com.hnrylvo.inmomarket.utils.isPasswordValid
import com.hnrylvo.inmomarket.utils.isEmailValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class FieldType {
    NAME,
    LASTNAME,
    EMAIL,
    PASSWORD,
    CONFIRM_PASSWORD,
    REGISTER_ENABLED
}

class RegisterViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _lastname = MutableStateFlow("")
    val lastname = _lastname.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _passwordsMatch = MutableStateFlow(false)

    private val _enabled = MutableStateFlow(false)
    val enabled = _enabled.asStateFlow()

    fun onValueChanged(fieldType: FieldType, value: String) {
        when (fieldType) {
            FieldType.NAME -> _name.update { value }
            FieldType.LASTNAME -> _lastname.update { value }
            FieldType.EMAIL -> _email.update { value }
            FieldType.PASSWORD -> {
                _password.update { value }
                checkFormEnabled()
            }

            FieldType.CONFIRM_PASSWORD -> {
                _confirmPassword.update { value }
                checkPasswordsMatch()
                checkFormEnabled()
            }

            FieldType.REGISTER_ENABLED -> checkFormEnabled()
        }
    }

    private fun checkPasswordsMatch() {
        _passwordsMatch.value = _password.value == _confirmPassword.value
    }

    private fun checkFormEnabled() {
        val isFormEnabled = _email.value.isEmailValid() &&
                _password.value.isPasswordValid() &&
                _passwordsMatch.value &&
                _name.value.isNotEmpty() &&
                _lastname.value.isNotEmpty()
        _enabled.value = isFormEnabled
    }

    fun register() {
        val request = RegisterRequest(
            name = _name.value,
            lastname = _lastname.value,
            email = _email.value,
            password = _password.value
        )
        viewModelScope.launch {
            authRepository.register(request).collectLatest { response ->
                response.onSuccess {
                    Log.d("RegisterViewModel", "Register successful")
                }
                response.onFailure {
                    Log.e("RegisterViewModel", "Register failed: $it")
                }
            }
        }
    }
}
