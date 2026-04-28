package com.ceej.common.expensetracker.signup

import androidx.lifecycle.ViewModel
import com.ceej.common.use_case.ValidateEmail
import com.ceej.common.use_case.ValidatePassword
import com.ceej.common.use_case.ValidateUsername
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel(
    private val validateUsername: ValidateUsername = ValidateUsername(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val reducer: SignUpReducer = SignUpReducer()
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    fun onEvent(event: SignUpEvent) {
        _state.update { reducer.reduce(it, event) }
        
        when (event) {
            is SignUpEvent.UsernameChanged -> {
                val result = validateUsername.execute(event.username)
                onEvent(SignUpEvent.UsernameErrorUpdated(result))
            }
            is SignUpEvent.PasswordChanged -> {
                val result = validatePassword.execute(event.password)
                onEvent(SignUpEvent.PasswordErrorUpdated(result))
            }
            is SignUpEvent.EmailChanged -> {
                val isValid = validateEmail.execute(event.email)
                onEvent(SignUpEvent.EmailValidityUpdated(isValid))
            }
            else -> {}
        }
    }
}
