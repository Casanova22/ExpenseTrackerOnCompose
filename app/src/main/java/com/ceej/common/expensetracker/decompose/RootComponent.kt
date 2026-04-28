package com.ceej.common.expensetracker.decompose

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ceej.common.expensetracker.login.component.LoginComponent
import com.ceej.common.expensetracker.signup.component.SignUpComponent
import com.ceej.common.expensetracker.splash.component.SplashComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Splash(val component: SplashComponent) : Child()
        class Login(val component: LoginComponent) : Child()
        class SignUp(val component: SignUpComponent) : Child()
    }
}
