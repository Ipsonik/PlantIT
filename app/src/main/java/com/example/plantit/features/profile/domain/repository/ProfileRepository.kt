package com.example.plantit.features.profile.domain.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.profile.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(userId: String): Resource<Profile>
}