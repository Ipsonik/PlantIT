package com.example.plantit.features.user_plants.domain.repository

import com.example.plantit.core.common.Resource
import com.example.plantit.features.user_plants.data.remote.model.CreateUserPlantDto
import com.example.plantit.features.user_plants.domain.model.UserPlant

interface UserPlantRepository {
    suspend fun addUserPlant(createPlantDto: CreateUserPlantDto): Resource<Unit>
    suspend fun getUserPlants(userId: String): Resource<List<UserPlant>>
    suspend fun deleteUserPlant(id: Int): Resource<Unit>

}