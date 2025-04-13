package com.miniproject01.BankingService.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "username")
    var username: String,
    @Column(name = "password")
    var password: String,

    @OneToMany
    var accounts: MutableList<AccountEntity?> = mutableListOf()

)
