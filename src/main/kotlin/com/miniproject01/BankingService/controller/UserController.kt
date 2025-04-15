package com.miniproject01.BankingService.controller

import com.miniproject01.BankingService.dto.KYCRequest
import com.miniproject01.BankingService.dto.KYCResponse
import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.KYCRepository
import com.miniproject01.BankingService.repository.UserRepository
import com.miniproject01.BankingService.service.UserService
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException.BadRequest
import java.math.BigDecimal
import java.util.*

@RestController
class UserController(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val kycRepository: KYCRepository
) {

    @PostMapping("/users/v1/register")
    fun register(@RequestBody user: UserEntity) = userService.registerUser(user)

    @PostMapping("/users/v1/kyc")
    fun createUpdateKYC(@RequestBody kycRequest: KYCRequest): KYCResponse {
        val user = userRepository.findById(kycRequest.userId).get()

        val existingKYC = kycRepository.findByUser(user)

        val kyc = existingKYC?.copy(
            firstName = kycRequest.firstName,
            lastName = kycRequest.lastName,
            dateOfBirth = kycRequest.dateOfBirth,
            nationality = kycRequest.nationality,
            salary = kycRequest.salary,
        )
            ?: KYCEntity(
                firstName = kycRequest.firstName,
                lastName = kycRequest.lastName,
                dateOfBirth = kycRequest.dateOfBirth,
                nationality = kycRequest.nationality,
                salary = kycRequest.salary,
                user = user
            )

        userService.createOrUpdateKYC(kyc)

        return KYCResponse(
            userId = user.id!!,
            firstName = kyc.firstName,
            lastName = kyc.lastName,
            dateOfBirth = kyc.dateOfBirth,
            salary = kyc.salary
        )
    }

    @GetMapping("/users/v1/kyc/{user_id}")
    fun getKYC(@PathVariable user_id: Long) :KYCEntity {
        return userService.getKYC(user_id) ?: KYCEntity(
            id = null,
            firstName = "",
            lastName = "",
            dateOfBirth = Date(),
            nationality = "",
            salary = BigDecimal.ZERO,
            user =  null
        )
    }
}



