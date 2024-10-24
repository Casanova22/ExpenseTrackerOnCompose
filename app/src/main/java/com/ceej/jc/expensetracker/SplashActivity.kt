package com.ceej.jc.expensetracker

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets.Side
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
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
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        } else {
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }

        // Set splash content
        setContent {
            SplashScreen(onTimeOut = { navigateToMainActivity() })
        }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

@Composable
fun SplashScreen(onTimeOut: ()-> Unit) {

    LaunchedEffect(Unit) {
        delay(3000)
        onTimeOut()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
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
            text = "Expense\nTracker",
            fontFamily = FontFamily(Font(R.font.varela_round)),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = Color(0xFFFFFFFF)
        )
    }
}