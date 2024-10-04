package com.ceej.jc.expensetracker

data class PasswordValidationState(
    val hasUppercaseCharacter : Boolean = false,
    val hasSpecialCharacter : Boolean = false,
    val hasNumericalCharacter : Boolean = false,
    val passwordSuccessful : Boolean = false
)
