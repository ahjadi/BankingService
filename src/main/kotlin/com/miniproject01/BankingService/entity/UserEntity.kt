package com.miniproject01.BankingService.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var username: String,

    var password: String,

    // CHECK THIS
    @OneToOne(mappedBy = "id")
    var KYC: KYCEntity,

    // CHECK THIS
    @OneToMany(mappedBy = "id")
    var accounts: MutableList<AccountEntity>

)