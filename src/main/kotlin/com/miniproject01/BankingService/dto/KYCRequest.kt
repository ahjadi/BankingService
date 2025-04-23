package com.miniproject01.BankingService.dto

import java.math.BigDecimal
import java.util.*

data class KYCRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date,
    val nationality: String,
    val salary: BigDecimal,
)
