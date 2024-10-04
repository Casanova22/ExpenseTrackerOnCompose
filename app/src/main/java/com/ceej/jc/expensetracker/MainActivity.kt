package com.ceej.jc.expensetracker

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ceej.jc.expensetracker.ui.theme.ExpenseTrackerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private const val TAG = "MainScreen"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayLoginScreen()
                    HideSystemBarAndNavBar()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayLoginScreen() {
    val navController = rememberNavController()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPasword by remember { mutableStateOf("") }


    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login") {
            LoginScreen(
                username = username,
                password = password,
                onUsernameChange = { username = it },
                onPasswordChange = { password = it },
                onLoginClick = {
                    navController.navigate("MainMenu")
                    Log.d(TAG, "LoginScreen")
                },
                onSignUpClick = {
                    navController.navigate("SignUpScreen")
                    Log.d(TAG, "LoginScreen")
                }
            )
        }

        composable("MainMenu") {

        }

        composable("SignUpScreen") {
            SignUpScreen(
                userName = username,
                onUserNameChange = { username = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                confirmPassword = confirmPasword,
                onConfirmPasswordChange = { confirmPasword = it },
                onSignUpClick = { /*TODO*/ },
                isLoading = null
            )
        }
    }


}



@Composable
fun HideSystemBarAndNavBar() {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        systemUiController.isNavigationBarVisible = false
        systemUiController.setStatusBarColor(color = Color(0xFF398C86) )
    }
}