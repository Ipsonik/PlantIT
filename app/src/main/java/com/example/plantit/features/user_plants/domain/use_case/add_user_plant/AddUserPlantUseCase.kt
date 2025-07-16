package com.example.plantit.features.user_plants.domain.use_case.add_user_plant

import com.example.plantit.core.common.Resource
import com.example.plantit.features.user_plants.data.remote.model.CreateUserPlantDto
import com.example.plantit.features.user_plants.domain.repository.UserPlantRepository

class AddUserPlantUseCase(
    private val repository: UserPlantRepository
) {
    suspend operator fun invoke(userPlantDto: CreateUserPlantDto): Resource<Unit> {
        return repository.addUserPlant(userPlantDto)
    }
}
