package com.example.plantit.features.plant_search.domain.use_case.search_plants

import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import com.example.plantit.features.plant_search.domain.repository.PlantsSearchRepository

class SearchPlantsUseCase(private val plantsRepository: PlantsSearchRepository) {
    suspend operator fun invoke(query: String): Resource<List<PlantDto>> {
        return plantsRepository.searchPlants(query)
    }
}