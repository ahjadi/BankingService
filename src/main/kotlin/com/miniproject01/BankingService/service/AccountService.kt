package com.miniproject01.BankingService.service

import com.miniproject01.BankingService.entity.AccountEntity
import com.miniproject01.BankingService.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {
    fun createNewAccount(account: AccountEntity) = accountRepository.save(account)
    fun deactivateAccount(account_number: String) {
        var account =
            accountRepository.getByAccountNumber(account_number)
                ?: throw NullPointerException("No account exists for account number $account_number")
        account.isActive = false
        accountRepository.save(account)
    }

    fun listAllAccounts() = accountRepository.findAll()


}