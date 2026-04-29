package com.ceej.expensetracker.modules

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


object AppBrushes {
    val splashBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF5DACA6),
            Color(0xFF468C87)
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )
}