package com.miniproject01.BankingService.controller

import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.KYCRepository
import com.miniproject01.BankingService.repository.UserRepository
import com.miniproject01.BankingService.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date

@RestController
class UserController(
    private val userService: UserService,
    val userRepository: UserRepository,
    val kycRepository: KYCRepository
) {

    @PostMapping("/users/v1/register")
    fun register(@RequestBody user: UserEntity) = userService.registerUser(user)

    @PostMapping("/users/v1/kyc")
    fun createUpdateKYC(@RequestBody kycRequest: KYCRequest): KYCResponse {
        val user = userRepository.findById(kycRequest.user_id).get()
        val existingKYC = kycRepository.findByUser(user)
        val kyc = existingKYC?.copy(
            first_name = kycRequest.first_name,
            last_name = kycRequest.last_name,
            date_of_birth = kycRequest.date_of_birth,
            nationality = kycRequest.nationality,
            salary = kycRequest.salary,
        ) ?: KYCEntity(
            first_name = kycRequest.first_name,
            last_name = kycRequest.last_name,
            date_of_birth = kycRequest.date_of_birth,
            nationality = kycRequest.nationality,
            salary = kycRequest.salary,
            user = user
        )

        userService.createOrUpdateKYC(kyc)
        val userId: Long = user.id!!
        return KYCResponse(
            user_id = userId,
            first_name = kyc.first_name,
            last_name = kyc.last_name,
            date_of_birth = kyc.date_of_birth,
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

