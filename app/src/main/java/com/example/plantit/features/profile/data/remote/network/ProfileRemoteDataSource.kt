package com.example.plantit.features.profile.data.remote.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.profile.data.remote.model.ProfileDto

interface ProfileRemoteDataSource {
    suspend fun getProfile(userId: String): Resource<ProfileDto>

}