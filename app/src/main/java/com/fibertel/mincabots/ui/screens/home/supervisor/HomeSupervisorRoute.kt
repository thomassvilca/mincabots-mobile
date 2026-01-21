package com.fibertel.mincabots.ui.screens.home.supervisor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.core.session.SessionState

@Composable
fun HomeSupervisorRoute(
    sessionManager: SessionManager,
    onGoValidate: () -> Unit,
    onGoHistory: () -> Unit,
    onOpenProfile: () -> Unit
) {
    val session by sessionManager.sessionFlow.collectAsState(initial = SessionState())

    val nombre = "Supervisor"
    val conectado = session.isLoggedIn

    HomeSupervisorScreen(
        nombre = nombre,
        conectado = conectado,
        onGoValidate = onGoValidate,
        onGoHistory = onGoHistory,
        onProfileClick = onOpenProfile
    )
}
