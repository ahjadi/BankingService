package com.miniproject01.BankingService.controller

import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @PostMapping("/users/v1/register")
    fun register(@RequestBody user: UserEntity) = userService.registerUser(user)
}