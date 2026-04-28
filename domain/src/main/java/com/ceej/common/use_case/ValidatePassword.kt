package com.ceej.common.use_case

import com.ceej.common.validation.PasswordValidationState

class ValidatePassword {
    fun execute(password: String): PasswordValidationState {
        val hasUppercase = password.any { it.isUpperCase() }
        val hasNumeric = password.any { it.isDigit() }
        val hasSpecialCharacter = password.any { !it.isLetterOrDigit() }
        val isMinLengthReached = password.length >= 8

        return PasswordValidationState(
            hasUppercaseCharacter = hasUppercase,
            hasNumericalCharacter = hasNumeric,
            hasSpecialCharacter = hasSpecialCharacter,
            isMinLengthReached = isMinLengthReached,
            passwordSuccessful = hasUppercase && hasNumeric && hasSpecialCharacter && isMinLengthReached
        )
    }
}
