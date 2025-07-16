package com.example.plantit.features.plant_detail.presentation

import com.example.plantit.features.plant_search.domain.model.Plant

data class PlantDetailState(
    val plant: Plant? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val dialogOpened : Boolean = false,
    val isAdded: Boolean = false
)
