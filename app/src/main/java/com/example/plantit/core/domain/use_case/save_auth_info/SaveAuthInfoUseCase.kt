package com.example.plantit.core.domain.use_case.save_auth_info

import com.example.plantit.features.login.data.remote.network.model.AuthResponse
import com.example.plantit.features.login.domain.AuthStorage

class SaveAuthInfoUseCase(
    private val authStorage: AuthStorage
) {
    suspend operator fun invoke(authResponse: AuthResponse) {
        authStorage.saveAccessToken(authResponse.accessToken ?: "")
        authStorage.saveRefreshToken(authResponse.refreshToken ?: "")
        authStorage.saveUser(authResponse.user)
        authResponse.expiresAt?.let { authStorage.saveExpiresAt(it) }
    }
}