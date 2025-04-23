package com.miniproject01.BankingService.dto

import java.math.BigDecimal
import java.util.*

data class KYCResponse(
    var firstName: String,
    var lastName: String,
    var dateOfBirth: Date,
    var salary: BigDecimal
)
