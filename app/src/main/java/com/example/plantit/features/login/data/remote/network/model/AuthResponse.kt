package com.example.plantit.features.login.data.remote.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("access_token")
    val accessToken: String? = null,
    @SerialName("refresh_token")
    val refreshToken: String? = null,
    val user: UserResponse? = null,
    val code: Int? = null,
    @SerialName("error_code")
    val error: String? = null,
    @SerialName("msg")
    val errorMessage: String? = null,
    @SerialName("expires_at")
    val expiresAt: Long? = null
)

@Serializable
data class UserResponse(
    val id: String,
    val email: String
)

