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

    var first_name: String,
    var last_name: String,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    val date_of_birth: Date,
    var nationality: String,
    var salary: BigDecimal,

    @OneToOne
    var user: UserEntity?
)

