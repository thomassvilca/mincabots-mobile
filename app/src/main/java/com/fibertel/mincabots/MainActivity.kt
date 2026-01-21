package com.fibertel.mincabots

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.fibertel.mincabots.core.session.SessionManager
import com.fibertel.mincabots.ui.navigation.MainLayout
import com.fibertel.mincabots.ui.navigation.MincabotsNavGraph
import com.fibertel.mincabots.ui.theme.MINCABOTSMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            MINCABOTSMobileTheme {
                val navController = rememberNavController()
                val context = LocalContext.current.applicationContext
                val sessionManager = remember { SessionManager(context) }

                MainLayout(navController, sessionManager) { inner ->
                    MincabotsNavGraph(navController, inner)
                }
            }
        }
    }
}