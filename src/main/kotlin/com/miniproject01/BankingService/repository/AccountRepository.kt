package com.miniproject01.BankingService.repository

import com.miniproject01.BankingService.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long>{
  fun getByAccountNumber(accountNumber: String) : AccountEntity?
}