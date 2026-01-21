package com.fibertel.mincabots.ui.screens.home.tecnico

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material.icons.outlined.Rule
import com.fibertel.mincabots.ui.components.MincaActionButton
import com.fibertel.mincabots.ui.components.MincaInfoCard
import com.fibertel.mincabots.ui.components.MincaSecondaryButton

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onAts: () -> Unit,
    onIperc: () -> Unit,
    onPets: () -> Unit,
    onPetar: () -> Unit,
    onMisFormularios: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.Top
    ) {
        MincaInfoCard(
            title = "¡Hola, ${uiState.nombre.ifBlank { "Usuario" }}!",
            subtitle = "Tu seguridad es nuestra prioridad. Gestiona tus formularios aquí.",
            status = if (uiState.conectado) "Conectado" else "Offline"
        )

        Spacer(Modifier.height(18.dp))

        MincaActionButton(
            text = "Completar ATS",
            icon = Icons.Outlined.Description,
            onClick = onAts
        )
        Spacer(Modifier.height(12.dp))

        MincaActionButton(
            text = "Completar IPERC",
            icon = Icons.Outlined.Assignment,
            onClick = onIperc
        )
        Spacer(Modifier.height(12.dp))

        MincaActionButton(
            text = "Completar PETS",
            icon = Icons.Outlined.Rule,
            onClick = onPets
        )
        Spacer(Modifier.height(12.dp))

        MincaActionButton(
            text = "Completar PETAR",
            icon = Icons.Outlined.FactCheck,
            onClick = onPetar
        )

        Spacer(Modifier.height(16.dp))

        MincaSecondaryButton(
            text = "Mis formularios",
            onClick = onMisFormularios
        )
    }
}
