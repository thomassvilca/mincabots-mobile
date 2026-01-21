package com.fibertel.mincabots.ui.screens.home.tecnico

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fibertel.mincabots.ui.screens.home.tecnico.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onGoAts: () -> Unit,
    onGoIperc: () -> Unit,
    onGoPets: () -> Unit,
    onGoPetar: () -> Unit,
    onGoMisFormularios: () -> Unit,
    onOpenProfile: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    HomeScreen(
        uiState = uiState,
        onAts = onGoAts,
        onIperc = onGoIperc,
        onPets = onGoPets,
        onPetar = onGoPetar,
        onMisFormularios = onGoMisFormularios,
        onProfileClick = onOpenProfile
    )
}
