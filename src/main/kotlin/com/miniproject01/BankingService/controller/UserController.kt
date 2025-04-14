package com.miniproject01.BankingService.controller

import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.KYCRepository
import com.miniproject01.BankingService.repository.UserRepository
import com.miniproject01.BankingService.service.UserService
import org.springframework.web.bind.annotation.*
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
        val user = userRepository.findById(kycRequest.user_id).get() //  need to put Null Exception later
        val existingKYC = kycRepository.findByUser(user)
        val kyc = existingKYC?.copy(
            firstName = kycRequest.first_name,
            lastName = kycRequest.last_name,
            dateOfBirth = kycRequest.date_of_birth,
            nationality = kycRequest.nationality,
            salary = kycRequest.salary,
        ) ?: KYCEntity(
            firstName = kycRequest.first_name,
            lastName = kycRequest.last_name,
            dateOfBirth = kycRequest.date_of_birth,
            nationality = kycRequest.nationality,
            salary = kycRequest.salary,
            user = user
        )
        userService.createOrUpdateKYC(kyc)
        val userId: Long = user.id!!
        return KYCResponse(
            user_id = userId,
            first_name = kyc.firstName,
            last_name = kyc.lastName,
            date_of_birth = kyc.dateOfBirth,
            salary = kyc.salary
        )
    }

    @GetMapping("/users/v1/kyc/{user_id}")
    fun getKYC(@PathVariable user_id: Long) = userService.getKYC(user_id)
}

data class KYCRequest(
    val first_name: String,
    val last_name: String,
    val date_of_birth: Date,
    val nationality: String,
    val salary: BigDecimal,
    val user_id: Long
)

data class KYCResponse(
    var user_id: Long,
    var first_name: String,
    var last_name: String,
    var date_of_birth: Date,
    var salary: BigDecimal
)

