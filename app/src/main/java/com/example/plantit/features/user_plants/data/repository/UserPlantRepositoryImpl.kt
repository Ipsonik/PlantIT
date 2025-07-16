package com.example.plantit.features.user_plants.data.repository


import com.example.plantit.core.common.Resource
import com.example.plantit.features.user_plants.data.remote.model.CreateUserPlantDto
import com.example.plantit.features.user_plants.data.remote.model.toUserPlant
import com.example.plantit.features.user_plants.data.remote.network.UserPlantRemoteDataSource
import com.example.plantit.features.user_plants.domain.model.UserPlant
import com.example.plantit.features.user_plants.domain.repository.UserPlantRepository

class UserPlantRepositoryImpl(
    private val remoteDataSource: UserPlantRemoteDataSource
) : UserPlantRepository {
    override suspend fun addUserPlant(createPlantDto: CreateUserPlantDto): Resource<Unit> {
        return remoteDataSource.addUserPlant(createPlantDto)
    }

    override suspend fun getUserPlants(userId: String): Resource<List<UserPlant>> {
        return when (val res = remoteDataSource.getUserPlants(userId)) {
            is Resource.Success -> {
                val list = res.data.orEmpty().map { it.toUserPlant() }
                Resource.Success(list)
            }

            is Resource.Error -> Resource.Error(res.message ?: "Nieznany błąd")
            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }

    override suspend fun deleteUserPlant(id: Int): Resource<Unit> {
        return remoteDataSource.deleteUserPlant(id)
    }

}