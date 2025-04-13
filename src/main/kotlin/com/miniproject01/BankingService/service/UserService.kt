package com.miniproject01.BankingService.service

import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun registerUser(user: UserEntity) = userRepository.save(user)
}