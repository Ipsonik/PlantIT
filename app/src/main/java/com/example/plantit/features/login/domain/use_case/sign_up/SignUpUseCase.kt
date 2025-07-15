package com.example.plantit.features.login.domain.use_case.sign_up

import com.example.plantit.core.common.Resource
import com.example.plantit.features.login.data.remote.network.model.AuthResponse
import com.example.plantit.features.login.domain.repository.AuthRepository

class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<AuthResponse> {
        return repository.signUp(email, password)
    }

}