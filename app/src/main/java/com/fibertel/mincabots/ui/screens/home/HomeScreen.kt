package com.fibertel.mincabots.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.ui.navigation.Routes
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Home Tecnico (placeholder)")

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

