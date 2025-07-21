package com.example.plantit.features.plant_search.presentation

import com.example.plantit.features.plant_search.domain.model.Plant

data class PlantListState(
    val query: String = "",
    val plants: List<Plant> = emptyList(),
    val isAdmin : Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isDialogOpen: Boolean = false,
    // fields for adding new plant by admin
    val newName: String = "",
    val newPhoto: String = "",
    val newWatering: String = "",
    val newFertilization: String = "",
    val newLight: String = "",
    val newTempMin: String = "",
    val newTempMax: String = "",
    val newSoil: String = ""
)
