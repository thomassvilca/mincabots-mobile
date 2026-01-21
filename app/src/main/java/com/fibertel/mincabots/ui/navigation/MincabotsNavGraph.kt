package com.fibertel.mincabots.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.data.local.db.DatabaseProvider
import com.fibertel.mincabots.data.repository.AuthRepository
import com.fibertel.mincabots.ui.screens.home.tecnico.HomeRoute
import com.fibertel.mincabots.ui.screens.home.tecnico.HomeViewModel
import com.fibertel.mincabots.ui.screens.home.supervisor.HomeSupervisorRoute
import com.fibertel.mincabots.ui.screens.login.LoginRoute
import com.fibertel.mincabots.ui.screens.login.LoginViewModel
import com.fibertel.mincabots.ui.screens.profile.ProfileRoute
import com.fibertel.mincabots.ui.screens.splash.SplashRoute

@Composable
fun MincabotsNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current.applicationContext

    val db = remember { DatabaseProvider.get(context) }
    val authRepository = remember { AuthRepository(db.usuarioDao()) }
    val sessionManager = remember { SessionManager(context) }

    val loginViewModel = remember { LoginViewModel(authRepository, sessionManager) }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {

        composable(Routes.SPLASH) {
            SplashRoute(
                sessionManager = sessionManager,
                onNavigate = { destination ->
                    navController.navigate(destination) {
                        popUpTo(navController.graph.id) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginRoute(
                viewModel = loginViewModel,
                onGoHome = {
                    navController.navigate(Routes.SPLASH) {
                        popUpTo(navController.graph.id) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.HOME_TECNICO) {
            val homeViewModel = remember { HomeViewModel(sessionManager, db.usuarioDao()) }
            HomeRoute(
                viewModel = homeViewModel,
                onGoAts = { navController.navigate(Routes.ATS_GENERAL) },
                onGoIperc = { navController.navigate(Routes.IPERC_GENERAL) },
                onGoPets = { navController.navigate(Routes.PETS_GENERAL) },
                onGoPetar = { navController.navigate(Routes.PETAR_GENERAL) },
                onGoMisFormularios = { navController.navigate(Routes.FORMS_LIST) },
                onOpenProfile = { navController.navigate(Routes.PROFILE) }
            )
        }

        composable(Routes.HOME_SUPERVISOR) {
            HomeSupervisorRoute(
                sessionManager = sessionManager,
                onGoValidate = { navController.navigate(Routes.VALIDATE_INBOX) },
                onGoHistory = { navController.navigate(Routes.HISTORY) },
                onOpenProfile = { navController.navigate(Routes.PROFILE) }
            )
        }


        composable(Routes.PROFILE) {
            ProfileRoute(
                sessionManager = sessionManager,
                onLoggedOut = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(navController.graph.id) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.ATS_GENERAL) { PlaceholderScreen("ATS (Placeholder)") }
        composable(Routes.IPERC_GENERAL) { PlaceholderScreen("IPERC (Placeholder)") }
        composable(Routes.PETS_GENERAL) { PlaceholderScreen("PETS (Placeholder)") }
        composable(Routes.FORMS_LIST) { PlaceholderScreen("Mis Formularios (Placeholder)") }
        composable(Routes.PETAR_GENERAL) {
            PlaceholderScreen(title = "PETAR (Placeholder)")
        }


        // NUEVAS SUPERVISOR
        composable(Routes.HISTORY) { PlaceholderScreen("Historial (Placeholder)") }
        composable(Routes.VALIDATE_INBOX) { PlaceholderScreen("Validar (Placeholder)") }
    }
}

@Composable
private fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { Text(title) }
}
