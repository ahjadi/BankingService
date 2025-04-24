package com.miniproject01.BankingService.controller

import com.miniproject01.BankingService.dto.KYCRequest
import com.miniproject01.BankingService.dto.KYCResponse
import com.miniproject01.BankingService.dto.UserRequest
import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.repository.KYCRepository
import com.miniproject01.BankingService.repository.UserRepository
import com.miniproject01.BankingService.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/public/users/v1/register")
    fun register(@RequestBody user: UserRequest): Any {
        return try {
            userService.registerUser(user)
        } catch (error: IllegalArgumentException) {
            ResponseEntity.badRequest().body("error: ${error.message}")
        }
    }

    @PostMapping("/users/v1/kyc")
    fun createUpdateKYC(@RequestBody kycRequest: KYCRequest): Any {
        val userId =
            userService.extractUserIdFromJwtToken()
        val user = userService.getUserById(userId!!) //  safe assertion because of prior null check
        val existingKYC = userService.getKYC(userId)

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
            firstName = kyc.firstName,
            lastName = kyc.lastName,
            dateOfBirth = kyc.dateOfBirth,
            salary = kyc.salary
        )
    }

    @GetMapping("/users/v1/kyc")
    fun getKYC(): KYCResponse {

        val userId = userService.extractUserIdFromJwtToken()
        val kyc = userService.getKYC(userId!!)

        return KYCResponse(
            firstName = kyc!!.firstName,
            lastName = kyc.lastName,
            dateOfBirth = kyc.dateOfBirth,
            salary = kyc.salary
        )
    }
}



