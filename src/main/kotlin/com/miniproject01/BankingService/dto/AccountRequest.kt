package com.miniproject01.BankingService.dto

import java.math.BigDecimal

data class AccountRequest(
    val userId: Long,
    val accountName: String,
    val balance: BigDecimal
)