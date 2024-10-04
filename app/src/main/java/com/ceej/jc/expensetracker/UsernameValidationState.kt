package com.ceej.jc.expensetracker

data class UsernameValidationState(
    val usernameContainsLetters : Boolean = false,
    val usernameContainsNumbers : Boolean = false,
    val usernameContainsSpecialCharacters : Boolean = false,
    val usernameContainsSpaces : Boolean = false,
    val usernameSuccessful : Boolean = true
)
