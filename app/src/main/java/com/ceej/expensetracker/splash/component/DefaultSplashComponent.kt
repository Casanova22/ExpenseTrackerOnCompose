package com.ceej.expensetracker.splash.component

import com.arkivanov.decompose.ComponentContext

class DefaultSplashComponent(
    componentContext: ComponentContext,
    private val onSplashFinished: () -> Unit
) : SplashComponent, ComponentContext by componentContext {
    override fun onFinished() {
        onSplashFinished()
    }
}
