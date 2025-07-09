package com.example.plantit.features.plant_search.domain.model

data class Plant(
    val id : Int,
    val name: String,
    val photo : String,
    val plantRequirement: PlantRequirement,
)