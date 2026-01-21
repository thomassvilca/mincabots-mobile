package com.fibertel.mincabots.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    showSearch: Boolean = false,
    onSearch: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Outlined.ArrowBack, contentDescription = "Atrás")
            }
        },
        actions = {
            if (showSearch) {
                IconButton(onClick = onSearch) {
                    Icon(Icons.Outlined.Search, contentDescription = "Buscar")
                }
            }
        }
    )
}
