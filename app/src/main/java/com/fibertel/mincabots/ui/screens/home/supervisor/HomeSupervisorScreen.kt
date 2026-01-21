package com.fibertel.mincabots.ui.screens.home.supervisor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.History
import com.fibertel.mincabots.ui.components.MincaActionButton
import com.fibertel.mincabots.ui.components.MincaInfoCard
import com.fibertel.mincabots.ui.components.MincaSecondaryButton

@Composable
fun HomeSupervisorScreen(
    nombre: String,
    conectado: Boolean,
    onGoValidate: () -> Unit,
    onGoHistory: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.Top
    ) {
        MincaInfoCard(
            title = "¡Hola, ${nombre.ifBlank { "Supervisor" }}!",
            subtitle = "Revisa formularios pendientes y valida reportes del área.",
            status = if (conectado) "Conectado" else "Offline"
        )

        Spacer(Modifier.height(18.dp))

        // Acción principal del supervisor
        MincaActionButton(
            text = "Validar pendientes",
            icon = Icons.Outlined.CheckCircle,
            onClick = onGoValidate
        )

        Spacer(Modifier.height(12.dp))

        // Acción secundaria
        MincaSecondaryButton(
            text = "Ver historial",
            onClick = onGoHistory
        )

        Spacer(Modifier.height(12.dp))

    }
}
