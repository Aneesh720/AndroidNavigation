package com.example.testing.data

data class Users(
    val id: Int,
    val email:String,
    val password:String,
)

enum class LOGIN_STATUS{
    INVALID_USER,
    INVALID_PASSWORD,
    UNKNOWN_ERROR,
    SUCCESS,

}