package com.ceej.common.expensetracker.signup

import com.ceej.common.validation.PasswordValidationState
import com.ceej.common.validation.UsernameValidationState

class SignUpReducer {
    fun reduce(state: SignUpState, event: SignUpEvent): SignUpState {
        return when (event) {
            is SignUpEvent.UsernameChanged -> state.copy(username = event.username)
            is SignUpEvent.EmailChanged -> state.copy(email = event.email)
            is SignUpEvent.PasswordChanged -> state.copy(password = event.password)
            is SignUpEvent.ConfirmPasswordChanged -> state.copy(confirmPassword = event.confirmPassword)
            is SignUpEvent.UsernameErrorUpdated -> state.copy(usernameError = event.error)
            is SignUpEvent.PasswordErrorUpdated -> state.copy(passwordError = event.error)
            is SignUpEvent.EmailValidityUpdated -> state.copy(isEmailValid = event.isValid)
            is SignUpEvent.LoadingChanged -> state.copy(isLoading = event.isLoading)
        }
    }
}

sealed class SignUpEvent {
    data class UsernameChanged(val username: String) : SignUpEvent()
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpEvent()
    data class UsernameErrorUpdated(val error: UsernameValidationState) : SignUpEvent()
    data class PasswordErrorUpdated(val error: PasswordValidationState) : SignUpEvent()
    data class EmailValidityUpdated(val isValid: Boolean) : SignUpEvent()
    data class LoadingChanged(val isLoading: Boolean) : SignUpEvent()
}
