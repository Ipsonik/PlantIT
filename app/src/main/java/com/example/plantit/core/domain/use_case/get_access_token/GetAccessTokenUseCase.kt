package com.example.plantit.core.domain.use_case.get_access_token

import com.example.plantit.features.login.domain.AuthStorage

class GetAccessTokenUseCase(
    private val authStorage: AuthStorage
) {
    suspend operator fun invoke(): String? {
        return authStorage.getAccessToken()
    }
}