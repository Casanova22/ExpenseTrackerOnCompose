package com.ceej.expensetracker.decompose

import com.arkivanov.decompose.ComponentContext

class DefaultLoginComponent(
    componentContext: ComponentContext,
    private val onLogin: () -> Unit,
    private val onSignUp: () -> Unit,
) : LoginComponent, ComponentContext by componentContext,
    com.ceej.expensetracker.login.component.LoginComponent {

    override fun onLoginClicked() {
        onLogin()
    }

    override fun onSignUpClicked() {
        onSignUp()
    }
}
