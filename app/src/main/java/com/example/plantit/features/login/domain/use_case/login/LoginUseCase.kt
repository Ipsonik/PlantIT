package com.example.plantit.features.login.domain.use_case.login

import com.example.plantit.core.common.Resource
import com.example.plantit.features.login.data.remote.network.model.AuthResponse
import com.example.plantit.features.login.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<AuthResponse> {
        return repository.login(email, password)
    }
}
