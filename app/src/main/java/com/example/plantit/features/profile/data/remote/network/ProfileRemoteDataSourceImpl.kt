package com.example.plantit.features.profile.data.remote.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.profile.data.remote.model.ProfileDto
import io.ktor.client.HttpClient

class ProfileRemoteDataSourceImpl(
    private val client: HttpClient
) : ProfileRemoteDataSource  {
    override suspend fun getProfile(userId: String): Resource<ProfileDto> {

    }
}