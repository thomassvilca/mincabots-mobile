package com.fibertel.mincabots.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fibertel.mincabots.ui.navigation.Routes

@Composable
fun LoginScreen(navController: NavHostController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Login (placeholder)")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Routes.HOME) }) {
                Text("Entrar")
            }
        }
    }
}
