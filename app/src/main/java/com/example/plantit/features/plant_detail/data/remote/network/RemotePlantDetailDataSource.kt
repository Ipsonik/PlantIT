package com.example.plantit.features.plant_detail.data.remote.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto

interface RemotePlantDetailDataSource {
    suspend fun getPlantById(plantId: Int): Resource<PlantDto>
/*
    suspend fun addPlantToMyPlants(plantId: Int): Resource<Unit>
*/
}