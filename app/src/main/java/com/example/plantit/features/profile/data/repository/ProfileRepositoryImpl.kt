package com.example.plantit.features.profile.data.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.profile.data.remote.model.toProfile
import com.example.plantit.features.profile.data.remote.network.ProfileRemoteDataSource
import com.example.plantit.features.profile.domain.model.Profile
import com.example.plantit.features.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val remoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun getProfile(userId: String): Resource<Profile> {
        return when (val res = remoteDataSource.getProfile(userId)) {
            is Resource.Success -> {
                val profile = res.data?.toProfile() ?: return Resource.Error("Nieznany błąd")
                Resource.Success(profile)
            }
            is Resource.Error -> Resource.Error(res.message ?: "Nieznany błąd")
            is Resource.Loading -> {
                Resource.Loading()
            }

        }
    }
}