package com.miniproject01.BankingService.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "kyc")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val date_of_birth: String,

    var nationality: String,

    var salary: BigDecimal,

    @OneToMany(mappedBy = "id")
    var transactions: MutableList<TransactionEntity>
)