package com.example.plantit.features.user_plants.data.remote.network

import com.example.plantit.core.common.Constants
import com.example.plantit.core.common.Resource
import com.example.plantit.features.user_plants.data.remote.model.CreateUserPlantDto
import com.example.plantit.features.user_plants.data.remote.model.UserPlantDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import javax.inject.Named

class UserPlantRemoteDataSourceImpl(
    @Named("appClient") private val httpClient: HttpClient
) : UserPlantRemoteDataSource {
    override suspend fun addUserPlant(createUserPlantDto: CreateUserPlantDto): Resource<Unit> {
        return try {
            val response = httpClient.post("${Constants.BASE_URL}user_plants") {
                contentType(ContentType.Application.Json)
                setBody(createUserPlantDto)
            }
            return when {
                response.status.value in 200..299 -> Resource.Success(Unit)
                response.status == HttpStatusCode.Conflict -> Resource.Error("Ta roślina już znajduje się w Twojej kolekcji")
                else -> Resource.Error("Błąd serwera: ${response.status.description}")


            }
        } catch (e: Exception) {
            Resource.Error("Błąd dodawania rośliny: ${e.localizedMessage}")
        }
    }

    override suspend fun getUserPlants(userId: String): Resource<List<UserPlantDto>> {
        return try {
            val response: List<UserPlantDto> = httpClient.get("${Constants.BASE_URL}user_plants") {
                parameter("select", "*,plant:plants(*)")
                parameter("user_id", "eq.$userId")
            }.body()

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Błąd pobierania roślin użytkownika: ${e.localizedMessage}")
        }
    }

    override suspend fun deleteUserPlant(id: Int): Resource<Unit> {
        TODO("Not yet implemented")
    }
}