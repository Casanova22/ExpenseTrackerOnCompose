package com.ceej.common.expensetracker.modules

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.ceej.common.expensetracker.R

object Fonts {

    fun appMainFont() : TextStyle {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.varela_round))
        )
    }

    fun fontStyleAndSizeForOutlinedTextFields() : TextStyle {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.varela_round)),
            fontSize = 17.sp
        )
    }
}