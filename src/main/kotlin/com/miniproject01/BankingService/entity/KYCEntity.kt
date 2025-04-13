package com.miniproject01.BankingService.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.Date

@Entity
@Table(name = "kyc")
data class KYCEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val date_of_birth: Date,
    var nationality: String,
    var salary: BigDecimal,
    @OneToOne
    var user: UserEntity
)

