package com.ceej.common.use_case.expense

import com.ceej.common.request.Request
import com.ceej.common.model.Expense
import com.ceej.common.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class GetExpenses(
    private val repository: ExpenseRepository
) {
    operator fun invoke(): Flow<Request<List<Expense>>> {
        return repository.getExpenses()
    }
}
