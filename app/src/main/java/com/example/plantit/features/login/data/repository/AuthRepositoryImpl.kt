package com.example.plantit.features.login.data.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.login.data.remote.network.AuthRemoteDataSource
import com.example.plantit.features.login.data.remote.network.model.AuthResponse
import com.example.plantit.features.login.domain.repository.AuthRepository

data class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signUp(email: String, password: String): Resource<AuthResponse> {
        return remoteDataSource.signUp(email, password)
    }

    override suspend fun login(email: String, password: String): Resource<AuthResponse> {
        return remoteDataSource.login(email, password)
    }
}
