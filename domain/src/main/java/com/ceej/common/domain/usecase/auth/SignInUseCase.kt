package com.ceej.common.domain.usecase.auth

import com.ceej.common.domain.repository.AuthRepository

class SignInUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return authRepository.signIn(
            email = email,
            password = password
        )
    }
}