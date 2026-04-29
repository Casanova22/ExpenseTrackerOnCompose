package com.ceej.expensetracker.login.component

import com.arkivanov.decompose.ComponentContext

class DefaultLoginComponent(
    componentContext: ComponentContext,
    private val onLogin: () -> Unit,
    private val onSignUp: () -> Unit,
) : LoginComponent, ComponentContext by componentContext {

    override fun onLoginClicked() {
        onLogin()
    }

    override fun onSignUpClicked() {
        onSignUp()
    }
}
