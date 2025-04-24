package com.miniproject01.BankingService.service

import com.miniproject01.BankingService.dto.UserRequest
import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.KYCRepository
import com.miniproject01.BankingService.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val kycRepository: KYCRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun registerUser(user: UserRequest) {
        require(user.username.length <= 10) { "username too long" }
        require(user.password.length >= 8) { "password length should be greater than 7" }
        val newUser = UserEntity(username = user.username, password = passwordEncoder.encode(user.password))
        userRepository.save(newUser)
    }

    fun createOrUpdateKYC(kycEntity: KYCEntity) {
        kycRepository.save(kycEntity)
    }

    fun getKYC(userId: Long): KYCEntity? {
       return  kycRepository.findByUserId(userId)
    }

    fun getUserById(userId: Long) =
        userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found with id $userId") }


    fun extractUserIdFromJwtToken(): Long? {
        return userRepository.findByUsername(SecurityContextHolder.getContext().authentication.name)?.id
            ?: throw IllegalArgumentException("User Not Found")
    }


}