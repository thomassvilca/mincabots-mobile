package com.fibertel.mincabots.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MincaErrorBanner(
    message: String,
    modifier: Modifier = Modifier
) {
    val cs = MaterialTheme.colorScheme
    val ty = MaterialTheme.typography

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = cs.error.copy(alpha = 0.10f),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = message,
            color = cs.error,
            modifier = Modifier.padding(12.dp),
            style = ty.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
