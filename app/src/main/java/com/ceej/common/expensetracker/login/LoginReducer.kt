package com.ceej.common.expensetracker.login

class LoginReducer {
    fun reduce(state: LoginState, event: LoginEvent): LoginState {
        return when (event) {
            is LoginEvent.EmailChanged -> state.copy(email = event.email)
            is LoginEvent.PasswordChanged -> state.copy(password = event.password)
            is LoginEvent.LoadingChanged -> state.copy(isLoading = event.isLoading)
            is LoginEvent.ErrorOccurred -> state.copy(error = event.error)
        }
    }
}

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class LoadingChanged(val isLoading: Boolean) : LoginEvent()
    data class ErrorOccurred(val error: String?) : LoginEvent()
}
