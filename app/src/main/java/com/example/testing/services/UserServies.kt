package com.example.testing.services

import com.example.testing.data.LOGIN_STATUS
import com.example.testing.repository.UserRepository

class UserServies(private val userRepository: UserRepository) {
    fun loginUser(email: String, password: String): String {
        val status = userRepository.loginUser(email, password)
        return when (status) {
            LOGIN_STATUS.INVALID_USER -> "User does not exist"
            LOGIN_STATUS.INVALID_PASSWORD -> "Password is Invalid"
            LOGIN_STATUS.UNKNOWN_ERROR -> "Unknown Error occurred"
            LOGIN_STATUS.SUCCESS -> "Logged In Successfully"

        }
    }
}