package com.ceej.jc.expensetracker.modules

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor

object ButtonColors {

    fun mainButtonGradient(): Brush {
        return Brush.linearGradient(
            colors = listOf(Color(0xFF48908B), Color(0xFF62A8A3)),
            start = Offset(0F, 0F),
            end = Offset(1000F, 1000F)
        )
    }

    fun disabledButton(): Brush {
        return SolidColor(Color(0xFFE9E9E9))
    }
}
