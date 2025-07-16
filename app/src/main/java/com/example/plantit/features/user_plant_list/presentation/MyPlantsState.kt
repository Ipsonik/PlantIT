package com.example.plantit.features.user_plant_list.presentation

import com.example.plantit.features.user_plants.domain.model.UserPlant

data class UserPlantListState(
    val isLoading: Boolean = false,
    val plants: List<UserPlant> = emptyList(),
    val error: String? = null)