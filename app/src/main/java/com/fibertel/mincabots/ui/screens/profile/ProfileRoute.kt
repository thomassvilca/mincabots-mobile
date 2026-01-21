package com.fibertel.mincabots.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.fibertel.mincabots.core.session.SessionManager
import kotlinx.coroutines.launch

@Composable
fun ProfileRoute(
    sessionManager: SessionManager,
    onLoggedOut: () -> Unit
) {
    val scope = rememberCoroutineScope()

    ProfileScreen(
        onLogoutClick = {
            scope.launch {
                sessionManager.clearSession()
                onLoggedOut()
            }
        }
    )
}
