package com.fibertel.mincabots.ui.screens.login

data class LoginUiState(
    val dni: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
