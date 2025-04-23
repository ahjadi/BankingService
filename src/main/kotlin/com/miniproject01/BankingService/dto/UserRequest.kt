package com.miniproject01.BankingService.dto

import jakarta.persistence.Column

data class UserRequest (
    var username: String,
    var password: String,
    )