package com.fibertel.mincabots.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.core.session.SessionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    navController: NavHostController,
    sessionManager: SessionManager,
    content: @Composable (Modifier) -> Unit
) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route ?: ""

    val session by sessionManager.sessionFlow.collectAsState(initial = SessionState())
    val role = session.rol.orEmpty()

    var showNewSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val topBarType = NavUiRules.topBarType(currentRoute)
    val title = NavUiRules.titleFor(currentRoute)
    val showBottom = NavUiRules.showBottomBar(currentRoute)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            when (topBarType) {
                TopBarType.HOME -> HomeTopBar(
                    onProfileClick = { navController.navigate(Routes.PROFILE) }
                )
                TopBarType.APP -> AppTopBar(
                    title = title,
                    showSearch = currentRoute == Routes.FORMS_LIST || currentRoute == Routes.HISTORY,
                    onBack = { navController.popBackStack() }
                )
            }
        },
        bottomBar = {
            if (showBottom) {
                BottomBar(
                    navController = navController,
                    role = role,
                    onTecnicoNewClick = { showNewSheet = true }
                )
            }
        }
    ) { padding ->
        content(Modifier.padding(padding))
    }

    // Sheet "Nuevo" solo para Técnico
    if (showNewSheet && role.equals("TECNICO", ignoreCase = true)) {
        ModalBottomSheet(
            onDismissRequest = { showNewSheet = false },
            sheetState = sheetState,
            containerColor = Color.White
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("¿Qué quieres crear hoy?", modifier = Modifier.padding(vertical = 8.dp))
                Divider()

                ListItem(
                    headlineContent = { Text("ATS") },
                    supportingContent = { Text("Análisis de Trabajo Seguro") },
                    modifier = Modifier
                        .clickable {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                showNewSheet = false
                                navController.navigate(Routes.ATS_GENERAL)
                            }
                        }
                        .padding(vertical = 4.dp)
                )

                ListItem(
                    headlineContent = { Text("IPERC") },
                    supportingContent = { Text("Identificación de peligros y evaluación de riesgos") },
                    modifier = Modifier
                        .clickable {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                showNewSheet = false
                                navController.navigate(Routes.IPERC_GENERAL)
                            }
                        }
                        .padding(vertical = 4.dp)
                )

                ListItem(
                    headlineContent = { Text("PETS") },
                    supportingContent = { Text("Procedimiento Escrito de Trabajo Seguro") },
                    modifier = Modifier
                        .clickable {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                showNewSheet = false
                                navController.navigate(Routes.PETS_GENERAL)
                            }
                        }
                        .padding(vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
