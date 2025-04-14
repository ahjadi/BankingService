package com.miniproject01.BankingService.service

import com.miniproject01.BankingService.entity.TransactionEntity
import com.miniproject01.BankingService.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionRepository: TransactionRepository
) {
    fun transferMoney(transaction: TransactionEntity) = transactionRepository.save(transaction)
}