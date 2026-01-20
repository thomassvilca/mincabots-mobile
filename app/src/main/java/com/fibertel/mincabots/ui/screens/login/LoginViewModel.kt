package com.fibertel.mincabots.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    fun onDniChange(value: String) {
        _uiState.update { it.copy(dni = value.filter(Char::isDigit).take(8), error = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, error = null) }
    }

    fun login() {
        val dni = uiState.value.dni
        val password = uiState.value.password

        if (dni.length != 8 || password.isBlank()) {
            _uiState.update { it.copy(error = "Completa tu DNI (8 dígitos) y contraseña.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val user = authRepository.login(dni, password)

            if (user == null) {
                _uiState.update { it.copy(isLoading = false, error = "Credenciales incorrectas.") }
                return@launch
            }

            // guardar sesión
            sessionManager.saveSession(userId = user.idUsuario, rol = user.rol)

            _uiState.update { it.copy(isLoading = false) }
            _loginSuccess.value = true
        }
    }

    fun consumeLoginSuccess() {
        _loginSuccess.value = false
    }
}
