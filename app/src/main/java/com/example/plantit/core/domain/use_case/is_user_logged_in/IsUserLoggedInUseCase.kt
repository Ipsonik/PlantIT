package com.example.plantit.core.domain.use_case.is_user_logged_in

import com.example.plantit.features.login.domain.AuthStorage

class IsUserLoggedInUseCase(
    private val authStorage: AuthStorage
) {
    suspend operator fun invoke(): Boolean {
        return !authStorage.getAccessToken().isNullOrBlank()
    }
}