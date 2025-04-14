package com.miniproject01.BankingService.controller

import com.miniproject01.BankingService.entity.AccountEntity
import com.miniproject01.BankingService.repository.UserRepository
import com.miniproject01.BankingService.service.AccountService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal


@RestController
class AccountController(
    val accountService: AccountService,
    val userRepository: UserRepository
) {

    @PostMapping("/accounts/v1/accounts")
    fun createAccount(@RequestBody request: AccountRequest): AccountResponse {
        val user = userRepository.findById(request.userId).orElseThrow { NoSuchElementException("no user found") }
        val account = AccountEntity(balance = request.balance, accountName = request.accountName, user = user)
        accountService.createNewAccount(account)

        return AccountResponse(
            userId = user.id!!,
            accountNumber = account.accountNumber,
            accountName = account.accountName,
            balance = account.balance
        )
    }

    @PostMapping("/accounts/v1/accounts/{account_number}/close")
    fun closeAccount(@PathVariable account_number: String) = accountService.deactivateAccount(account_number)
}

    data class AccountRequest(
        val userId: Long, // From UserEntity
        val accountName: String,
        val balance: BigDecimal
    )

    data class AccountResponse(
        val userId: Long, // From UserEntity
        val accountNumber: String,
        val accountName: String,
        val balance: BigDecimal
    )
