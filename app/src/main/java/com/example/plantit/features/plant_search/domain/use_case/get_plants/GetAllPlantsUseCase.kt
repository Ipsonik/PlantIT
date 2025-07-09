package com.example.plantit.features.plant_search.domain.use_case.get_plants

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import com.example.plantit.features.plant_search.domain.repository.PlantsSearchRepository

class GetAllPlantsUseCase(private val plantsRepository: PlantsSearchRepository) {
    suspend operator fun invoke(): Resource<List<PlantDto>> {
        return plantsRepository.getPlants()
    }
}
