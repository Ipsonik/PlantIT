package com.example.plantit.core.data.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto

interface RemotePlantSearchDataSource {
    suspend fun getAllPlants(): Resource<List<PlantDto>>
    suspend fun searchPlant(query: String): Resource<List<PlantDto>>
}