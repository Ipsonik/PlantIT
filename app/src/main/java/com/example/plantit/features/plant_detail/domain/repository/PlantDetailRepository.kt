package com.example.plantit.features.plant_detail.domain.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto

interface PlantDetailRepository {
    suspend fun getPlantById(id: Int): Resource<PlantDto>
/*
    suspend fun addPlantToUserList(plantId: Int): Resource<Unit>
*/
}