package com.ceej.common.domain.usecase.auth

import com.ceej.common.domain.repository.AuthRepository

class IsUserLoggedInUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn()
    }
}