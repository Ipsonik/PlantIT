package com.example.plantit.features.plant_detail.domain.use_case

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_detail.domain.repository.PlantDetailRepository

data class AddPlantToMyCollectionUseCase(
    val repository: PlantDetailRepository
) {
    /*suspend operator fun invoke(plantId: String): Resource<Unit> {
        return repository.addPlantToUserList(plantId)
    }*/
}
