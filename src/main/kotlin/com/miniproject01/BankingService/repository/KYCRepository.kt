package com.miniproject01.BankingService.repository

import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KYCRepository : JpaRepository<KYCEntity, Long>{

    //Derived Queries
    fun findByUser(user: UserEntity) : KYCEntity
    fun findByUserId(userId: Long) : KYCEntity
}