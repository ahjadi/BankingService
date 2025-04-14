package com.miniproject01.BankingService.service

import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.KYCRepository
import com.miniproject01.BankingService.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val kycRepository: KYCRepository
) {

    fun registerUser(user: UserEntity) = userRepository.save(user)

    fun createOrUpdateKYC(kycEntity: KYCEntity) = kycRepository.save(kycEntity)

    fun getKYC(userId: Long) = kycRepository.findByUserId(userId)


}