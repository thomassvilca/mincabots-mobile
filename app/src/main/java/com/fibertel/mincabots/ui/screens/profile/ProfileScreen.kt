package com.fibertel.mincabots.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    onLogoutClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Perfil (placeholder)")
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onLogoutClick) {
                Text("Cerrar sesión")
            }
        }
    }
}
