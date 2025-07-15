package com.example.plantit.features.login.presentation.utils

object AuthErrorMapper {
    fun fromCode(code: String?): AuthErrorType {
        return when (code) {
            "email_address_invalid" -> AuthErrorType.InvalidEmail
            "email_not_confirmed" -> AuthErrorType.EmailNotConfirmed
            "invalid_credentials" -> AuthErrorType.InvalidCredentials
            "validation_failed" -> AuthErrorType.ValidationFailed

            else -> AuthErrorType.Unknown
        }
    }
}