package com.fibertel.mincabots.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fibertel.mincabots.ui.navigation.Routes
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.fibertel.mincabots.core.session.SessionManager

@Composable
fun HomeSupervisorScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Home Supervisor (placeholder)")

            Spacer(modifier = Modifier.height(16.dp))

            // BOTÓN TEMPORAL DE LOGOUT (DEV)
            Button(
                onClick = {
                    scope.launch {
                        sessionManager.clearSession()
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.HOME_TECNICO) { inclusive = true }
                        }
                    }
                }
            ) {
                Text("Cerrar sesión (temporal)")
            }
        }

    }
}
