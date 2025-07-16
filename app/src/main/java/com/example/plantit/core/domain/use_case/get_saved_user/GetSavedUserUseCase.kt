package com.example.plantit.core.domain.use_case.get_saved_user

import com.example.plantit.features.login.data.remote.network.model.UserResponse
import com.example.plantit.features.login.domain.AuthStorage

class GetSavedUserUseCase(
    private val authStorage: AuthStorage
) {
    suspend operator fun invoke(): UserResponse? {
        return authStorage.getUser()
    }
}