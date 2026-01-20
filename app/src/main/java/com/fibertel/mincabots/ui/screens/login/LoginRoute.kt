package com.fibertel.mincabots.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun LoginRoute(
    viewModel: LoginViewModel,
    onGoHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val loginSuccess by viewModel.loginSuccess.collectAsState()

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            viewModel.consumeLoginSuccess()
            onGoHome()
        }
    }

    LoginScreen(
        uiState = uiState,
        onDniChange = viewModel::onDniChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLogin = viewModel::login
    )
}
