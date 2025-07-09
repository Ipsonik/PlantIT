package com.example.plantit.features.plant_search.domain.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto

interface PlantsSearchRepository {
    suspend fun getPlants(): Resource<List<PlantDto>>
    suspend fun searchPlants(query: String): Resource<List<PlantDto>>
}