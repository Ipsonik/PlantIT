package com.example.plantit.features.login.domain

import android.content.Context
import com.example.plantit.features.login.data.remote.network.model.UserResponse

interface AuthStorage {
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun saveUser(user: UserResponse?)
    suspend fun getAccessToken(): String?
    suspend fun getUser(): UserResponse?
    suspend fun clear()
    suspend fun saveExpiresAt(timestamp: Long)
    suspend fun getExpiresAt(): Long?
}