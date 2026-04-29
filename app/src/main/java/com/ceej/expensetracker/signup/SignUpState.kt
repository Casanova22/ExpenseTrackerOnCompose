package com.ceej.expensetracker.signup

import com.ceej.common.validation.PasswordValidationState
import com.ceej.common.validation.UsernameValidationState

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val usernameError: UsernameValidationState = UsernameValidationState(),
    val passwordError: PasswordValidationState = PasswordValidationState(),
    val isEmailValid: Boolean = true,
    val isLoading: Boolean = false
)
