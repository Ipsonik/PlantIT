package com.example.plantit.core.domain.use_case.session_valid

import com.example.plantit.features.login.domain.AuthStorage

class IsSessionValidUseCase (
    private val authStorage: AuthStorage
) {
    suspend operator fun invoke(): Boolean {
        val expiresAt = authStorage.getExpiresAt() ?: return false
        return expiresAt > (System.currentTimeMillis() / 1000)
    }

}