package com.example.plantit.model

data class Plant(
    val id : Int,
    val name: String,
    val photo : String,
    val plantRequirement: PlantRequirement,
)
