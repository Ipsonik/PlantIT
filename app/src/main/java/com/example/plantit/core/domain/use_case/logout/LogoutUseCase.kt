package com.example.plantit.core.domain.use_case.logout

import com.example.plantit.features.login.domain.AuthStorage

class LogoutUseCase (
   private val authStorage: AuthStorage
) {
    suspend operator fun invoke() {
        authStorage.clear()
    }
}