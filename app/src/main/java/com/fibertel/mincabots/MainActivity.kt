package com.fibertel.mincabots

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.fibertel.mincabots.ui.navigation.MincabotsNavGraph
import com.fibertel.mincabots.ui.theme.MINCABOTSMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MINCABOTSMobileTheme {
                val navController = rememberNavController()
                MincabotsNavGraph(navController = navController)
            }
        }
    }
}
