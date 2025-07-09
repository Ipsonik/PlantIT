package com.example.plantit.features.plant_detail.data.remote.network

import com.example.plantit.BuildConfig
import com.example.plantit.core.common.Constants
import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter

class KtorRemotePlantDetailDataSource(private val httpClient: HttpClient) : RemotePlantDetailDataSource {
    override suspend fun getPlantById(plantId: Int): Resource<PlantDto> {
        return try {
            val response = httpClient.get("${Constants.BASE_URL}plants") {
                parameter("select", "*")
                parameter("id", "eq.$plantId")
                headers {
                    append("apikey", BuildConfig.SUPABASE_KEY)
                    append("Authorization", "Bearer ${BuildConfig.SUPABASE_KEY}")
                }
            }.body<List<PlantDto>>() // Supabase zawsze zwraca listę

            val plant = response.firstOrNull()
            if (plant != null) {
                Resource.Success(plant)
            } else {
                Resource.Error("Roślina o ID $plantId nie została znaleziona.")
            }
        } catch (e: Exception) {
            Resource.Error("Nie udało się pobrać rośliny: ${e.localizedMessage}")
        }
    }


   /* override suspend fun addPlantToMyPlants(plantId: Int): Resource<PlantDto> {

    }*/
}