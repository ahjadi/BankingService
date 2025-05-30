package com.miniproject01.BankingService.repository

import com.miniproject01.BankingService.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long > {
    fun findByUsername(username: String): UserEntity?
}