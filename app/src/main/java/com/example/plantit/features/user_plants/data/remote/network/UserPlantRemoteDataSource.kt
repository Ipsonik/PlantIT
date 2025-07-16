package com.example.plantit.features.user_plants.data.remote.network

import com.example.plantit.core.common.Resource
import com.example.plantit.features.user_plants.data.remote.model.CreateUserPlantDto
import com.example.plantit.features.user_plants.data.remote.model.UserPlantDto

interface UserPlantRemoteDataSource {
    suspend fun addUserPlant(createUserPlantDto: CreateUserPlantDto): Resource<Unit>
    suspend fun getUserPlants(userId: String): Resource<List<UserPlantDto>>
    suspend fun deleteUserPlant(id: Int): Resource<Unit>
}