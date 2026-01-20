package com.fibertel.mincabots.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.core.session.SessionState
import com.fibertel.mincabots.ui.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    val session by sessionManager.sessionFlow.collectAsState(
        initial = SessionState()
    )

    LaunchedEffect(session) {
        delay(500)

        val destination = when {
            !session.isLoggedIn -> Routes.LOGIN
            session.rol == "TECNICO" -> Routes.HOME_TECNICO
            session.rol == "SUPERVISOR" -> Routes.HOME_SUPERVISOR
            else -> Routes.LOGIN
        }

        navController.navigate(destination) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("MINCABOTS")
    }
}
