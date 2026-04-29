package com.ceej.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowInsetsControllerCompat
import com.arkivanov.decompose.defaultComponentContext
import com.ceej.expensetracker.decompose.DefaultRootComponent
import com.ceej.expensetracker.ui.theme.ExpenseTrackerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext()
        )

        setContent {
            ExpenseTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootContent(root)
                    HideSystemBarAndNavBar()
                }
            }
        }
    }
}

@Composable
fun HideSystemBarAndNavBar() {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        systemUiController.isNavigationBarVisible = false
        systemUiController.setStatusBarColor(color = Color(0xFF398C86))
    }
}
