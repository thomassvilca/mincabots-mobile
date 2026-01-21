package com.fibertel.mincabots.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fibertel.mincabots.ui.theme.BrandBackgroundLight
import com.fibertel.mincabots.ui.theme.BrandTextGray

@Composable
fun MincaInfoCard(
    title: String,
    subtitle: String,
    status: String? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BrandBackgroundLight)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = BrandTextGray
            )

            if (status != null) {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = status,
                    fontSize = 12.sp,
                    color = BrandTextGray
                )
            }
        }
    }
}
