package com.example.plantit.features.login.presentation

import com.example.plantit.features.login.data.remote.network.model.UserResponse
import com.example.plantit.features.login.presentation.utils.AuthErrorType

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val authError: String? = null,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val user: UserResponse? = null,
    val authErrorType: AuthErrorType? = null
)
