package com.miniproject01.BankingService.controller

import com.miniproject01.BankingService.dto.AccountRequest
import com.miniproject01.BankingService.dto.AccountResponse
import com.miniproject01.BankingService.dto.TransactionRequest
import com.miniproject01.BankingService.dto.TransactionResponse
import com.miniproject01.BankingService.entity.AccountEntity
import com.miniproject01.BankingService.entity.TransactionEntity
import com.miniproject01.BankingService.repository.AccountRepository
import com.miniproject01.BankingService.repository.UserRepository
import com.miniproject01.BankingService.service.AccountService
import com.miniproject01.BankingService.service.SameAccountTransferException
import com.miniproject01.BankingService.service.TransactionService
import org.springframework.web.bind.annotation.*


@RestController
class AccountController(
    private val accountService: AccountService,
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository,
    private val transactionService: TransactionService
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

    @GetMapping("/accounts/v1/accounts")
    fun listAccounts() = accountService.listAllAccounts()

    @PostMapping("/accounts/v1/accounts/transfer")
    fun transfer(@RequestBody request: TransactionRequest): TransactionResponse {
        return try{
            transactionService.transferMoney(request)
        } catch (e: SameAccountTransferException) {
            println("Cannot transfer to the same account: ${e.message}")
        } catch (e: NullPointerException) {
            println("One of the accounts is null: ${e.message}")
        } catch (e: RuntimeException) {
            println("Validation failed: ${e.message}")
        } as TransactionResponse


            }
        }

