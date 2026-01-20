package com.fibertel.mincabots.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.data.local.db.DatabaseProvider
import com.fibertel.mincabots.data.repository.AuthRepository
import com.fibertel.mincabots.ui.screens.home.HomeScreen
import com.fibertel.mincabots.ui.screens.home.HomeSupervisorScreen
import com.fibertel.mincabots.ui.screens.login.LoginRoute
import com.fibertel.mincabots.ui.screens.login.LoginViewModel
import com.fibertel.mincabots.ui.screens.splash.SplashScreen

@Composable
fun MincabotsNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val db = remember { DatabaseProvider.get(context) }
    val authRepository = remember { AuthRepository(db.usuarioDao()) }
    val sessionManager = remember { SessionManager(context) }
    val loginViewModel = remember { LoginViewModel(authRepository, sessionManager) }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {

        // SPLASH
        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        // LOGIN
        composable(Routes.LOGIN) {
            LoginRoute(
                viewModel = loginViewModel,
                onGoHome = {
                    navController.navigate(Routes.SPLASH) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // HOME TÉCNICO
        composable(Routes.HOME_TECNICO) {
            HomeScreen(navController = navController)
        }

        // HOME SUPERVISOR
        composable(Routes.HOME_SUPERVISOR) {
            HomeSupervisorScreen(navController = navController)
        }
    }
}
