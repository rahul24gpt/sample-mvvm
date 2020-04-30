package com.architecture.util

class ValidationUtils {
    companion object{
        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}