package com.ceej.expensetracker.decompose

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.ceej.expensetracker.splash.component.DefaultSplashComponent
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Splash,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Splash -> RootComponent.Child.Splash(
                DefaultSplashComponent(
                    componentContext = componentContext,
                    onSplashFinished = {
                        navigation.replaceAll(Config.Login)
                    }
                )
            )

            is Config.Login -> RootComponent.Child.Login(
                DefaultLoginComponent(
                    componentContext = componentContext,
                    onLogin = {
                        // Handle login success
                    },
                    onSignUp = {
                        navigation.push(Config.SignUp)
                    }
                )
            )

            is Config.SignUp -> RootComponent.Child.SignUp(
                DefaultSignUpComponent(
                    componentContext = componentContext,
                    onSignUpSuccess = {
                        navigation.pop()
                    },
                    onBack = {
                        navigation.pop()
                    }
                )
            )
        }

    sealed interface Config : Parcelable {
        @Parcelize
        data object Splash : Config

        @Parcelize
        data object Login : Config

        @Parcelize
        data object SignUp : Config
    }
}
