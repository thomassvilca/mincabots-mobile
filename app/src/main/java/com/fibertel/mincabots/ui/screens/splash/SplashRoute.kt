package com.fibertel.mincabots.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.ui.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashRoute(
    sessionManager: SessionManager,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(300)
        val session = sessionManager.sessionFlow.first()

        val destination = when {
            !session.isLoggedIn -> Routes.LOGIN
            session.rol == "TECNICO" -> Routes.HOME_TECNICO
            session.rol == "SUPERVISOR" -> Routes.HOME_SUPERVISOR
            else -> Routes.LOGIN
        }

        onNavigate(destination)
    }

    SplashScreen()
}
