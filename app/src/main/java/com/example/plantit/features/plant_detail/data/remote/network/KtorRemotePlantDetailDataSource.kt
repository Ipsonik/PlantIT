package com.example.plantit.features.plant_detail.data.remote.network

import com.example.plantit.core.common.Constants
import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_detail.data.remote.network.RemotePlantDetailDataSource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodedPath
import javax.inject.Named

class KtorRemotePlantDetailDataSource(
    @Named("appClient") private val httpClient: HttpClient) : RemotePlantDetailDataSource {
    override suspend fun getPlantById(plantId: Int): Resource<PlantDto> {
        return try {
            val response = httpClient.get{
                url {
                    encodedPath += "plants"
                }
                parameter("select", "*")
                parameter("id", "eq.$plantId")

            }.body<List<PlantDto>>()

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
}