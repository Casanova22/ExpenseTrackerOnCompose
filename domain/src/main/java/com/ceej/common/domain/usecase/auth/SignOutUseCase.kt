package com.ceej.common.domain.usecase.auth

import com.ceej.common.domain.repository.AuthRepository

class SignOutUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return authRepository.signOut()
    }
}