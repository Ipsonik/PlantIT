package com.example.plantit.features.plant_search.data.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.core.data.network.RemotePlantSearchDataSource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import com.example.plantit.features.plant_search.domain.repository.PlantsSearchRepository

class PlantsRepositoryImpl(
    private val remoteDataSource: RemotePlantSearchDataSource
) : PlantsSearchRepository {

    override suspend fun getPlants(): Resource<List<PlantDto>> {
        return remoteDataSource.getAllPlants()
    }

    override suspend fun searchPlants(query: String): Resource<List<PlantDto>> {
        return remoteDataSource.searchPlant(query)
    }
}
