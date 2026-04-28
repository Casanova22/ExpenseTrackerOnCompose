package com.ceej.common.model

data class Expense(
    val id: String,
    val title: String,
    val amount: Double,
    val category: String,
    val date: String
)
