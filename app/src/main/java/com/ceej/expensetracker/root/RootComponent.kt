package com.ceej.expensetracker.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ceej.expensetracker.login.component.DefaultLoginComponent
import com.ceej.expensetracker.signup.component.DefaultSignUpComponent
import com.ceej.expensetracker.splash.component.SplashComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Splash(val component: SplashComponent) : Child()
        class Login(val component: DefaultLoginComponent) : Child()
        class SignUp(val component: DefaultSignUpComponent) : Child()
    }
}