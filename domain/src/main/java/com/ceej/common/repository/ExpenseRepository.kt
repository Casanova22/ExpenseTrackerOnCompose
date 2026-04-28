package com.ceej.common.repository

import com.ceej.common.request.Request
import com.ceej.common.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses(): Flow<Request<List<Expense>>>
}
