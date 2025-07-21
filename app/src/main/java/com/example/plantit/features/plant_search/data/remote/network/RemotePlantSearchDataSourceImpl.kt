package com.example.plantit.features.plant_search.data.remote.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.CreatePlantDto
import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import javax.inject.Named


class RemotePlantSearchDataSourceImpl(
    @Named("appClient") private val httpClient: HttpClient
) : RemotePlantSearchDataSource {

    override suspend fun getAllPlants(): Resource<List<PlantDto>> {
        return try {
            val response = httpClient.get {
                url {
                    encodedPath += "plants"
                }
                parameter("select", "*")
            }.body<List<PlantDto>>()

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Nie udało się pobrać roślin: ${e.localizedMessage}")
        }
    }


    override suspend fun searchPlant(query: String): Resource<List<PlantDto>> {
        return try {
            val response = httpClient.get {
                url {
                    encodedPath += "plants"
                }
                parameter("select", "*")
                parameter("name", "ilike.*$query*")
            }.body<List<PlantDto>>()

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Nie udało się wyszukać roślin: ${e.localizedMessage}")
        }
    }

    override suspend fun addPlant(createDto: CreatePlantDto): Resource<Unit> {
        return try {
             httpClient.post {
                url {
                    encodedPath += "plants"
                }
                contentType(ContentType.Application.Json)
                header("Prefer", "return=representation")
                setBody(createDto)
            }
            Resource.Success(Unit)
        } catch (e: ClientRequestException) {
            Resource.Error("Błąd dodawania rośliny: ${e.response.status.value}")
        } catch (e: Exception) {
            Resource.Error("Nieoczekiwany błąd: ${e.localizedMessage}")
        }
    }
}