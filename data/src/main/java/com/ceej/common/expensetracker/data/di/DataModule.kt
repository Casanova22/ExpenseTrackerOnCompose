package com.ceej.common.expensetracker.data.di

import com.ceej.common.expensetracker.data.remote.datasource.AuthRemoteDataSource
import com.ceej.common.expensetracker.data.remote.datasource.AuthRemoteDataSourceImpl
import com.ceej.common.domain.repository.AuthRepository
import com.ceej.common.expensetracker.data.repository.AuthRepositoryImpl
import com.ceej.common.expensetracker.data.supabase.SupabaseClientProvider
import io.github.jan.supabase.SupabaseClient
import org.koin.dsl.module

val dataModule = module {

    single<SupabaseClient>{
        SupabaseClientProvider.client
    }
    single<AuthRemoteDataSource>{
        AuthRemoteDataSourceImpl(
            supabaseClient = get()
        )
    }
    single<AuthRepository> {
        AuthRepositoryImpl(
            authRemoteDataSource = get()
        )
    }
}