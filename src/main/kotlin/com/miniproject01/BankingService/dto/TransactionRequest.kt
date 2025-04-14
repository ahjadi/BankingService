package com.miniproject01.BankingService.dto

import java.math.BigDecimal

data class TransactionRequest(
    val sourceAccount: String,
    val destinationAccount: String,
    val transferAmount: BigDecimal
)