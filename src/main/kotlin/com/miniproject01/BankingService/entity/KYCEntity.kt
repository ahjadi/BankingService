package com.miniproject01.BankingService.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "kyc")
data class KYCEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var firstName: String,
    var lastName: String,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    val dateOfBirth: Date,
    var nationality: String,
    var salary: BigDecimal,
    @OneToOne
    var user: UserEntity?
)

