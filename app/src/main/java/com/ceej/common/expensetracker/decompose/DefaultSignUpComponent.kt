package com.ceej.common.expensetracker.decompose

import com.arkivanov.decompose.ComponentContext

class DefaultSignUpComponent(
    componentContext: ComponentContext,
    private val onSignUpSuccess: () -> Unit,
    private val onBack: () -> Unit,
) : SignUpComponent, ComponentContext by componentContext {

    override fun onSignUpClicked() {
        onSignUpSuccess()
    }

    override fun onBackClicked() {
        onBack()
    }
}
