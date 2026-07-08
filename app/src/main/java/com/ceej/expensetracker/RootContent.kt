package com.ceej.expensetracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.ceej.expensetracker.utils.NetworkConnectivityObserver
import com.ceej.common.network.ConnectivityObserver
import com.ceej.expensetracker.composables.NoInternetAlertDialog
import com.ceej.expensetracker.login.LoginScreen
import com.ceej.expensetracker.root.RootComponent
import com.ceej.expensetracker.signup.SignUpScreen
import com.ceej.expensetracker.splash.splashScreen

@Composable
fun RootContent(component: RootComponent) {
    val context = LocalContext.current

    val connectivityObserver = remember {
        NetworkConnectivityObserver(context.applicationContext)
    }

    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val isNetworkAvailable = status == ConnectivityObserver.Status.Available

    if (!isNetworkAvailable) {
        NoInternetAlertDialog(
            onDismissRequest = {
                // Do nothing. Keep dialog open while no internet.
            }
        )
    }

    Children(
        stack = component.stack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Splash -> splashScreen(
                component = child.component,
                isNetworkAvailable = isNetworkAvailable
            )

            is RootComponent.Child.Login -> LoginScreen(child.component)
            is RootComponent.Child.SignUp -> SignUpScreen(child.component)
        }
    }
}