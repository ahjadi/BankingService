package com.miniproject01.BankingService.dto

import java.math.BigDecimal

data class TransactionResponse(
    val newBalanceOfSourceAccount: BigDecimal
)