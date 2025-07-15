package com.example.plantit.features.login.domain.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.login.data.remote.network.model.AuthResponse

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Resource<AuthResponse>
    suspend fun login(email: String, password: String): Resource<AuthResponse>

}