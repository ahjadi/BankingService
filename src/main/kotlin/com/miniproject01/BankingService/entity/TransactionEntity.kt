package com.miniproject01.BankingService.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var source_account: Long,
    var destination_account: Long,
    var amount: BigDecimal
)

