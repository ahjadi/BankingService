package com.miniproject01.BankingService

import com.miniproject01.BankingService.entity.KYCEntity
import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.UserRepository

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import java.math.BigDecimal
import java.text.SimpleDateFormat

@SpringBootApplication
class InitUserRunner {
    @Bean
    fun initUsers(userRepository: UserRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val user1 = UserEntity(username = "test1", password = passwordEncoder.encode("Test12345"))
        val user2 = UserEntity(username = "test2", password = passwordEncoder.encode("Test12345"))
        userRepository.save(user1)
        userRepository.save(user2)
        val kyc1 = KYCEntity(
            firstName = "Test1",
            lastName = "Altest1",
            dateOfBirth = SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"),
            nationality = "Kuwaiti",
            salary = BigDecimal("1750.25"),
            user = user1
        )

        val kyc2 = KYCEntity(
            firstName = "Test2",
            lastName = "Altest2",
            dateOfBirth = SimpleDateFormat("yyyy-MM-dd").parse("2000-04-01"),
            nationality = "American",
            salary = BigDecimal("3000.500"),
            user = user2)
    }
}

fun main(args: Array<String>) {
    runApplication<BankingServiceApplicationTests>(*args).close()
}