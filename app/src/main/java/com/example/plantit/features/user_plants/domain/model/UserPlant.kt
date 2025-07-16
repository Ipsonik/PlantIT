package com.example.plantit.features.user_plants.domain.model

import kotlinx.datetime.LocalDate

data class UserPlant(
    val id: Int,
    val plantId: Int,
    val userId: String,
    val customName: String? = null,
    val lastWatered: LocalDate? = null,
    val lastFertilized: LocalDate? = null,
    val customWateringInterval: Int? = null,
    val customFertilizationInterval: Int? = null,
    val photo: String? = null
)
