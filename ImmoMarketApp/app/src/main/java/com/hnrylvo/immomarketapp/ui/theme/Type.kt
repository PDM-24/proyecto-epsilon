package com.hnrylvo.immomarketapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hnrylvo.immomarketapp.R

// Set of Material typography styles to start with
val IstokWebFamily = FontFamily(
    Font(R.font.istokweb_regular, FontWeight.Normal),
    Font(R.font.istokweb_bold, FontWeight.Bold)
)

val MyTypography = Typography(
    titleSmall = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = SecondaryGreen
    ),
    titleMedium = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        color = SecondaryGreen
    ),
    titleLarge = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = SecondaryGreen
    ),
    bodyLarge = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = PrimaryGreen
    ),
    bodyMedium = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = PrimaryGreen
    ),
    bodySmall = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = PrimaryGreen
    ),
    labelSmall = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = PrimaryGreen
    ),
    labelLarge = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = PrimaryGreen
    ),
)