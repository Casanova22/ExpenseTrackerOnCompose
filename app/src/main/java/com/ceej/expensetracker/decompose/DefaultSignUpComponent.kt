package com.ceej.expensetracker.decompose

import com.arkivanov.decompose.ComponentContext

class DefaultSignUpComponent(
    componentContext: ComponentContext,
    private val onSignUpSuccess: () -> Unit,
    private val onBack: () -> Unit,
) : SignUpComponent, ComponentContext by componentContext,
    com.ceej.expensetracker.signup.component.SignUpComponent {

    override fun onSignUpClicked() {
        onSignUpSuccess()
    }

    override fun onBackClicked() {
        onBack()
    }
}
