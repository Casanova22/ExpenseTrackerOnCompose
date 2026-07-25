package com.ceej.common.expensetracker.data.remote.datasource

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthRemoteDataSourceImpl(
    private val supabaseClient: SupabaseClient
) : AuthRemoteDataSource {

    override suspend fun signUp(
        email: String,
        password: String
    ) {
        supabaseClient.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ) {
        supabaseClient.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    override suspend fun signOut() {
        supabaseClient.auth.signOut()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return supabaseClient.auth.currentUserOrNull() != null
    }
}