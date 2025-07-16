package com.example.plantit.features.user_plants.domain.use_case.get_user_plants

import com.example.plantit.features.user_plants.domain.repository.UserPlantRepository

class GetUserPlantsUseCase(
    private val repository: UserPlantRepository
) {
    suspend operator fun invoke(userId: String) = repository.getUserPlants(userId)
}