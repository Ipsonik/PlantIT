package com.example.plantit.features.plant_search.presentation

import com.example.plantit.features.plant_search.domain.model.Plant

data class PlantListState(
    val query: String = "",
    val plants: List<Plant> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
