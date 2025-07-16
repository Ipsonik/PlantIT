package com.example.plantit.features.profile.domain.use_case

import com.example.plantit.core.common.Resource
import com.example.plantit.features.profile.domain.model.Profile

class GetProfileUseCase (
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String): Resource<Profile> {
        return repository.getProfile(userId)
    }

}