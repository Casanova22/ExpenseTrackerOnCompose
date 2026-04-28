package com.ceej.common.validation

data class PasswordValidationState(
    val hasUppercaseCharacter : Boolean = false,
    val hasSpecialCharacter : Boolean = false,
    val hasNumericalCharacter : Boolean = false,
    val isMinLengthReached : Boolean = false,
    val passwordsMatch : Boolean = false,
    val passwordSuccessful : Boolean = false
)
