package com.ceej.expensetracker.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ceej.expensetracker.R
import com.ceej.expensetracker.splash.component.SplashComponent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(component: SplashComponent) {

    LaunchedEffect(Unit) {
        delay(3000)
        component.onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5DACA6),
                        Color(0xFF468C87)
                    ),
                    start = Offset(0F, 0F),
                    end = Offset(1000F, 1000F)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Expense\n\nTracker",
            fontFamily = FontFamily(Font(R.font.varela_round)),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }
}
