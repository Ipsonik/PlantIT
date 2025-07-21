package com.example.plantit.features.profile.data.remote.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.profile.data.remote.model.ProfileDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodedPath

class ProfileRemoteDataSourceImpl(
    private val client: HttpClient
) : ProfileRemoteDataSource {

    override suspend fun getProfile(userId: String): Resource<ProfileDto> = try {
        val response: List<ProfileDto> = client.get {
            url {
                encodedPath += "profiles"
            }
            parameter("user_id", "eq.$userId")
        }.body()
        if (response.isNotEmpty())
            Resource.Success(response.first())
        else
            Resource.Error("Nie znaleziono profilu")

    } catch (e: Exception) {
        Resource.Error(e.message ?: "Nieznany błąd")
    }
}