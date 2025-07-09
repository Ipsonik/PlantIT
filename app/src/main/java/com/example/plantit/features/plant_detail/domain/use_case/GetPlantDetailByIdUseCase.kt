package com.example.plantit.features.plant_detail.domain.use_case

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_detail.domain.repository.PlantDetailRepository
import com.example.plantit.features.plant_search.data.remote.model.PlantDto

data class GetPlantDetailByIdUseCase(
    val repository: PlantDetailRepository
) {
    suspend operator fun invoke(plantId: Int): Resource<PlantDto> {
        return repository.getPlantById(plantId)
    }
}
