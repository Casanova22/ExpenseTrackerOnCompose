package com.ceej.jc.expensetracker


class ValidateOutlinedTextFields {

    fun executeUsernameValidation(username: String) : UsernameValidationState {
        val validateContainsLetters = validateUsernameContainsLetters(username)
        val validateContainsNumbers = validateUsernameContainsNumbers(username)
        val validateContainsLimitedSpecialCharacters = validateUsernameContainsLimitedSpecialCharacters(username)

        val hasError = listOf(
            validateContainsLetters,
            validateContainsNumbers,
            validateContainsLimitedSpecialCharacters
        ).any { it }

        return UsernameValidationState(
            usernameContainsLetters = validateContainsLetters,
            usernameContainsNumbers = validateContainsNumbers,
            usernameContainsSpecialCharacters = validateContainsLimitedSpecialCharacters,
            usernameSuccessful =  !hasError
        )
    }

    fun executePasswordValidation(password: String) : PasswordValidationState {
        val validateHasUpper = validatePasswordHasUppercase(password)
        val validateHasNumeric = validatePasswordHasNumeric(password)
        val validateHasSpecial = validatePasswordHasSpecialCharacter(password)
        val validatePasswordsMatch =validatePasswordsMatch(password, confirmPassword = "")

        val hasError = listOf(
            validateHasUpper,
            validateHasNumeric,
            validateHasSpecial,
            validatePasswordsMatch

        ).all { it }

        return PasswordValidationState(
            hasUppercaseCharacter = validateHasUpper,
            hasNumericalCharacter = validateHasNumeric,
            hasSpecialCharacter =  validateHasSpecial,
            passwordSuccessful =  hasError
        )
    }

    //----------------------USERNAME REGEX VALIDATIONS---------------------------//
    private fun validateUsernameContainsLetters(username: String) : Boolean =
        username.contains(Regex("^[\\S]*[a-zA-Z][\\S]$"))

    private fun validateUsernameContainsNumbers(username: String) : Boolean =
        username.contains(Regex("^[\\S]*\\d[\\S]$"))

    private fun validateUsernameContainsLimitedSpecialCharacters(username: String) : Boolean =
        username.contains(Regex("^[\\S]*[._!][\\S]$"))

    //----------------------PASSWORD REGEX VALIDATIONS---------------------------//
    private fun validatePasswordHasUppercase(password: String): Boolean =
        password.contains(Regex(".*[A-Z].*"))  // Checks for at least one uppercase letter

    private fun validatePasswordHasNumeric(password: String): Boolean =
        password.contains(Regex(".*\\d.*"))  // Checks for at least one numeric digit

    private fun validatePasswordHasSpecialCharacter(password: String): Boolean =
        password.contains(Regex(".*[!@#$%^&*(),.?\":{}|<>].*"))  // Checks for at least one special character

    private fun validatePasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}