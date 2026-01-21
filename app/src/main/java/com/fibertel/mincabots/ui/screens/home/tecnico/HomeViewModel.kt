package com.fibertel.mincabots.ui.screens.home.tecnico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.data.local.dao.UsuarioDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val sessionManager: SessionManager,
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun load() {
        viewModelScope.launch {
            val session = sessionManager.sessionFlow.first()

            val userId = session.userId
            val rol = session.rol.orEmpty()

            if (userId.isNullOrBlank()) return@launch

            val usuario = usuarioDao.getById(userId)

            _uiState.update {
                it.copy(
                    nombre = usuario?.nombres.orEmpty(),
                    rol = rol,
                    conectado = true
                )
            }
        }
    }

}