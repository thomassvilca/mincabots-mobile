package com.fibertel.mincabots.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fibertel.mincabots.R

val Inter = FontFamily(
    Font(R.font.inter_variable, weight = FontWeight.W400),
    Font(R.font.inter_variable, weight = FontWeight.W500),
    Font(R.font.inter_variable, weight = FontWeight.W600),
    Font(R.font.inter_variable, weight = FontWeight.W700),
)

val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W700,
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 11.sp,
        lineHeight = 14.sp
    )
)