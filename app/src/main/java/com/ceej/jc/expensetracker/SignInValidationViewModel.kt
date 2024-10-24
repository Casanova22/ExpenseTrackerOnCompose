package com.ceej.jc.expensetracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class SignInValidationViewModel(
    private val validateOutlinedTextFields: ValidateOutlinedTextFields = ValidateOutlinedTextFields()
) : ViewModel() {

    var password by mutableStateOf("")
        private set
    var username by mutableStateOf("")
        private set

    val passwordError =
        snapshotFlow { password }
            .mapLatest { validateOutlinedTextFields.executePasswordValidation(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PasswordValidationState()
            )

    val usernameError =
        snapshotFlow { username }
            .mapLatest { validateOutlinedTextFields.executeUsernameValidation(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UsernameValidationState(
                    usernameContainsLetters = false,
                    usernameContainsNumbers = false,
                    usernameContainsSpecialCharacters = false,
                    usernameSuccessful = false
                )
            )


    fun changePassword(value: String) {
        password = value
    }

    fun changeUsername(value: String) {
        username = value
    }
}