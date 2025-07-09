package com.example.plantit.core.data.network

import com.example.plantit.BuildConfig
import com.example.plantit.core.common.Constants
import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter


class KtorRemotePlantSearchDataSource (private val httpClient: HttpClient) : RemotePlantSearchDataSource {

    override suspend fun getAllPlants(): Resource<List<PlantDto>> {
        return try {
            val response = httpClient.get("${Constants.BASE_URL}plants") {
                parameter("select", "*")
                headers {
                    append("apikey", BuildConfig.SUPABASE_KEY)
                    append("Authorization", "Bearer ${BuildConfig.SUPABASE_KEY}")
                }
            }.body<List<PlantDto>>()

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Nie udało się pobrać roślin: ${e.localizedMessage}")
        }
    }



    override suspend fun searchPlant(query: String): Resource<List<PlantDto>> {
        return try {
            val response = httpClient.get("${Constants.BASE_URL}plants") {
                parameter("select", "*")
                parameter("name", "ilike.*$query*")
                headers {
                    append("apikey", BuildConfig.SUPABASE_KEY)
                    append("Authorization", "Bearer ${BuildConfig.SUPABASE_KEY}")
                }
            }.body<List<PlantDto>>()

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Nie udało się wyszukać roślin: ${e.localizedMessage}")
        }
    }

}