package com.miniproject01.BankingService.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "accounts")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var balance: BigDecimal,
    var is_active: Boolean,
    var account_number: String,

    @ManyToOne
    val user: UserEntity,

    @OneToMany(mappedBy = "source_account")
    var srcTransactions: MutableList<TransactionEntity>,
    @OneToMany(mappedBy = "destination_account")
    var dstTransactions: MutableList<TransactionEntity>
)
