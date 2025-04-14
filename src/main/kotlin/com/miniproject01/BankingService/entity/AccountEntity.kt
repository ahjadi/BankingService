package com.miniproject01.BankingService.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "accounts")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var balance: BigDecimal = BigDecimal.ZERO, //
    var isActive: Boolean = true,
    @Column(name = "account_number")
    var accountNumber: String = UUID.randomUUID().toString(),
    var accountName: String,
    @ManyToOne
    val user: UserEntity, // for FK

//    @OneToMany(mappedBy = "source_account")
//    var srcTransactions: MutableList<TransactionEntity>,
//    @OneToMany(mappedBy = "destination_account")
//    var dstTransactions: MutableList<TransactionEntity>
)
