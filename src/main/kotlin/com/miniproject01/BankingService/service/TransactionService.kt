package com.miniproject01.BankingService.service

import com.miniproject01.BankingService.dto.TransactionRequest
import com.miniproject01.BankingService.dto.TransactionResponse
import com.miniproject01.BankingService.entity.TransactionEntity
import com.miniproject01.BankingService.repository.AccountRepository
import com.miniproject01.BankingService.repository.TransactionRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val accountRepository: AccountRepository,
) {
    fun transferMoney(transaction: TransactionRequest) :TransactionResponse{

        validateTransfer(transaction)
        val source = accountRepository.getByAccountNumber(transaction.sourceAccount)!!
        val destination = accountRepository.getByAccountNumber(transaction.destinationAccount)!!
        source.balance -= transaction.transferAmount
        destination.balance += transaction.transferAmount
        accountRepository.save(source)
        accountRepository.save(destination)

        val transactionEntity = TransactionEntity(
            sourceAccount = source.id!!,
            destinationAccount = destination.id!!,
            amount = transaction.transferAmount,
        )
        transactionRepository.save(transactionEntity)
        return TransactionResponse(source.balance)
    }

    fun validateTransfer(transaction: TransactionRequest) {
        val source = accountRepository.getByAccountNumber(transaction.sourceAccount)
        val destination = accountRepository.getByAccountNumber(transaction.destinationAccount)

        if (source == null)
            throw NullPointerException("Source account is null")
        if (destination == null)
            throw NullPointerException("Destination account is null")
        if (source == destination)
            throw SameAccountTransferException()
        if (source.isActive == false)
            throw RuntimeException("Source account is not active")
        if (destination.isActive == false)
            throw RuntimeException("Destination account is not active")
        if (source.balance < transaction.transferAmount)
            throw RuntimeException("Insufficient source balance")
        if (transaction.transferAmount < BigDecimal.ZERO)
            throw RuntimeException("Negative transfer amount")
    }
}

class SameAccountTransferException(message: String = "Cannot transfer money to the same account.") :
    RuntimeException(message) // just experimenting