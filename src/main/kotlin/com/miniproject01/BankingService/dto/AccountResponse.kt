package com.miniproject01.BankingService.dto

import java.math.BigDecimal

data class AccountResponse(
    val userId: Long,
    val accountNumber: String,
    val accountName: String,
    val balance: BigDecimal
)