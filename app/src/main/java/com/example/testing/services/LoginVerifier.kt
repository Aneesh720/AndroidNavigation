package com.example.testing.services

class LoginVerifier {

    fun conditionSatified(email: String, password: String): String {
        val result = passwordVerifier(password)
        return if (result != "Success") {
            result
        } else if (email.isEmpty()) {
            "Enter The email"
        } else if (!Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+").matches(email)) {
            "Enter the perfect Email in perfect Format"
        } else {
            "Success"
        }
    }
//android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun passwordVerifier(password: String): String {
        return if (!password.isLongEnough()) {
            "Password should be long enough in the Password"
        } else if (!password.hasEnoughDigits()) {
            "Add the Digits"
        } else if (!password.isMixedCase()) {
            "Add Capital as well as Lower case in the Password"
        } else if (!password.hasSpecialChar()) {
            "Add Special Character in the Password"
        } else {
            "Success"
        }
    }


    fun String.isLongEnough() = length >= 8
    fun String.hasEnoughDigits() = count(Char::isDigit) > 0
    fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)
    fun String.hasSpecialChar() = any { it in "!,+^*" }

}