package com.ceej.common.expensetracker.data.repository

import com.ceej.common.domain.repository.AuthRepository
import com.ceej.common.expensetracker.data.remote.datasource.AuthRemoteDataSource

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit> {
        return runCatching {
            authRemoteDataSource.signUp(
                email = email,
                password = password
            )
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<Unit> {
        return runCatching {
            authRemoteDataSource.signIn(
                email = email,
                password = password
            )
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return runCatching {
            authRemoteDataSource.signOut()
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return authRemoteDataSource.isUserLoggedIn()
    }
}