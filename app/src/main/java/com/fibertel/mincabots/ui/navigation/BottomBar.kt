package com.fibertel.mincabots.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fibertel.mincabots.ui.theme.BrandPrimary

@Composable
fun BottomBar(
    navController: NavHostController,
    role: String,
    onTecnicoNewClick: () -> Unit
) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route ?: ""

    val isTecnico = role.equals("TECNICO", ignoreCase = true)
    val isSupervisor = role.equals("SUPERVISOR", ignoreCase = true)

    val homeRoute =
        if (isSupervisor) Routes.HOME_SUPERVISOR else Routes.HOME_TECNICO

    val secondRoute =
        if (isSupervisor) Routes.HISTORY else Routes.FORMS_LIST

    val secondLabel =
        if (isSupervisor) "Historial" else "Formularios"

    Surface(
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp
        ) {

            // ───────── INICIO ─────────
            val homeSelected = currentRoute == homeRoute
            NavigationBarItem(
                selected = homeSelected,
                alwaysShowLabel = true,
                onClick = {
                    if (homeSelected) return@NavigationBarItem
                    navController.navigate(homeRoute) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector =
                            if (homeSelected) Icons.Filled.Home
                            else Icons.Outlined.Home,
                        contentDescription = "Inicio",
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = {
                    BottomLabel("Inicio", homeSelected)
                },
                colors = navColors()
            )

            // ───────── FORMULARIOS / HISTORIAL ─────────
            val secondSelected = currentRoute == secondRoute
            NavigationBarItem(
                selected = secondSelected,
                alwaysShowLabel = true,
                onClick = {
                    if (secondSelected) return@NavigationBarItem
                    navController.navigate(secondRoute) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector =
                            if (secondSelected) Icons.Filled.ListAlt
                            else Icons.Outlined.ListAlt,
                        contentDescription = secondLabel,
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = {
                    BottomLabel(secondLabel, secondSelected)
                },
                colors = navColors()
            )


            if (isTecnico) {
                NavigationBarItem(
                    selected = false,
                    alwaysShowLabel = true,
                    onClick = onTecnicoNewClick,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AddBox,
                            contentDescription = "Nuevo",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        BottomLabel("Nuevo", false)
                    },
                    colors = navColors()
                )
            } else if (isSupervisor) {
                val validateSelected =
                    currentRoute == Routes.VALIDATE_INBOX

                NavigationBarItem(
                    selected = validateSelected,
                    alwaysShowLabel = true,
                    onClick = {
                        if (validateSelected) return@NavigationBarItem
                        navController.navigate(Routes.VALIDATE_INBOX) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector =
                                if (validateSelected) Icons.Filled.CheckCircle
                                else Icons.Outlined.CheckCircle,
                            contentDescription = "Validar",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        BottomLabel("Validar", validateSelected)
                    },
                    colors = navColors()
                )
            }
        }
    }
}

@Composable
private fun BottomLabel(text: String, selected: Boolean) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium.copy(
            fontSize = if (selected) 14.sp else 12.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            letterSpacing = if (selected) 0.3.sp else 0.sp,
            lineHeight = 14.sp
        ),
        maxLines = 1
    )
}


@Composable
private fun navColors() =
    NavigationBarItemDefaults.colors(
        selectedIconColor = BrandPrimary,
        selectedTextColor = BrandPrimary,
        unselectedIconColor = Color(0xFF565D6D),
        unselectedTextColor = Color(0xFF565D6D),
        indicatorColor = Color.Transparent
    )
