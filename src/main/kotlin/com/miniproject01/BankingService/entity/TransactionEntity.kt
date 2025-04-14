package com.miniproject01.BankingService.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var sourceAccount: Long,
    var destinationAccount: Long,
    var amount: BigDecimal
)

