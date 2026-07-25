package com.ceej.common.expensetracker.data.remote.datasource

import io.github.jan.supabase.SupabaseClient

interface AuthRemoteDataSource  {

    suspend fun signUp(
        email: String,
        password: String
    )

    suspend fun signIn(
        email: String,
        password: String
    )
    suspend fun signOut()

    suspend fun isUserLoggedIn(): Boolean
}

