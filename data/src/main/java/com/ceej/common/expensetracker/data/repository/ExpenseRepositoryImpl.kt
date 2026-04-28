package com.ceej.common.expensetracker.data.repository

import com.ceej.common.request.Request
import com.ceej.common.expensetracker.data.remote.KtorClient
import com.ceej.common.expensetracker.data.remote.dto.ExpenseDto
import com.ceej.common.expensetracker.data.remote.dto.toExpense
import com.ceej.common.model.Expense
import com.ceej.common.repository.ExpenseRepository
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ExpenseRepositoryImpl : ExpenseRepository {
    private val client = KtorClient.httpClient

    override fun getExpenses(): Flow<Request<List<Expense>>> = flow {
        emit(Request.Loading())
        try {
            val response = client.get("expenses")
            val remoteExpenses = response.body<List<ExpenseDto>>()
            emit(Request.Success(remoteExpenses.map { it.toExpense() }))
        } catch (e: Exception) {
            emit(Request.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Request.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
