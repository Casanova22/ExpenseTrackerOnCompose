package com.ceej.common.expensetracker.splash.component

import com.arkivanov.decompose.ComponentContext

class DefaultSplashComponent(
    componentContext: ComponentContext,
    private val onSplashFinished: () -> Unit
) : SplashComponent, ComponentContext by componentContext {
    override fun onFinished() {
        onSplashFinished()
    }
}
