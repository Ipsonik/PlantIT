package com.example.plantit.features.plant_search.domain.use_case.add_plant

import com.example.plantit.features.plant_search.data.remote.model.CreatePlantDto
import com.example.plantit.features.plant_search.domain.repository.PlantsSearchRepository

class AddPlantUseCase(
    private val repository: PlantsSearchRepository
) {
    suspend operator fun invoke(createDto: CreatePlantDto) = repository.addPlant(createDto)
}