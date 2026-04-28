package com.ceej.common.expensetracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.ceej.common.expensetracker.data.network.NetworkConnectivityObserver
import com.ceej.common.expensetracker.decompose.RootComponent
import com.ceej.common.network.ConnectivityObserver
import com.ceej.common.expensetracker.login.LoginScreen
import com.ceej.common.expensetracker.modules.NoInternetAlertDialog
import com.ceej.common.expensetracker.signup.SignUpScreen
import com.ceej.common.expensetracker.splash.SplashScreen

@Composable
fun RootContent(component: RootComponent) {
    val context = LocalContext.current
    val connectivityObserver = remember { NetworkConnectivityObserver(context) }
    val status by connectivityObserver.observe().collectAsState(initial = ConnectivityObserver.Status.Available)
    
    var showNoInternetDialog by remember { mutableStateOf(false) }

    if (status != ConnectivityObserver.Status.Available) {
        showNoInternetDialog = true
    }

    if (showNoInternetDialog) {
        NoInternetAlertDialog(onDismissRequest = { showNoInternetDialog = false })
    }

    Children(
        stack = component.stack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Splash -> SplashScreen(child.component)
            is RootComponent.Child.Login -> LoginScreen(child.component)
            is RootComponent.Child.SignUp -> SignUpScreen(child.component)
        }
    }
}
