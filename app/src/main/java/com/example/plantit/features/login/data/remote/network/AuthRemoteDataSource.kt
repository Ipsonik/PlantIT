package com.example.plantit.features.login.data.remote.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.login.data.remote.network.model.AuthResponse

interface AuthRemoteDataSource {
    suspend fun signUp(email: String, password: String): Resource<AuthResponse>
    suspend fun login(email: String, password: String): Resource<AuthResponse>
}