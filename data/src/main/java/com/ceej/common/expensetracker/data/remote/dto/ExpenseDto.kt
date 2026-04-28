package com.ceej.common.expensetracker.data.remote.dto

import com.ceej.common.model.Expense
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExpenseDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("amount")
    val amount: Double,
    @SerialName("category")
    val category: String,
    @SerialName("date")
    val date: String
)

fun ExpenseDto.toExpense(): Expense {
    return Expense(
        id = id,
        title = title,
        amount = amount,
        category = category,
        date = date
    )
}
