package com.example.plantit.features.plant_detail.data.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_detail.data.remote.network.RemotePlantDetailDataSource
import com.example.plantit.features.plant_detail.domain.repository.PlantDetailRepository
import com.example.plantit.features.plant_search.data.remote.model.PlantDto

class PlantDetailRepositoryImpl (
    private val remotePlantDetailDataSource: RemotePlantDetailDataSource
) : PlantDetailRepository {

    override suspend fun getPlantById(id: Int): Resource<PlantDto> {
        return remotePlantDetailDataSource.getPlantById(id)
    }

    /*override suspend fun addPlantToUserList(plantId: Int): Resource<Unit> {
        return remotePlantDetailDataSource.addPlantToMyPlants(plantId)
    }*/
}