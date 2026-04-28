package com.ceej.common.use_case

import com.ceej.common.validation.UsernameValidationState

class ValidateUsername {
    fun execute(username: String): UsernameValidationState {
        val validateContainsLetters = username.contains(Regex(".*[a-zA-Z].*"))
        val validateContainsNumbers = username.contains(Regex(".*\\d.*"))
        val validateContainsLimitedSpecialCharacters = username.contains(Regex(".*[._!].*"))

        val hasError = !validateContainsLetters || !validateContainsNumbers || !validateContainsLimitedSpecialCharacters

        return UsernameValidationState(
            usernameContainsLetters = validateContainsLetters,
            usernameContainsNumbers = validateContainsNumbers,
            usernameContainsSpecialCharacters = validateContainsLimitedSpecialCharacters,
            usernameSuccessful = !hasError
        )
    }
}
