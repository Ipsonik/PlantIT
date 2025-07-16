package com.example.plantit.features.user_plants.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserPlantDto(
    @SerialName("plant_id")
    val plantId: Int,
    @SerialName("user_id")
    val userId: String,
    @SerialName("custom_name")
    val customName: String? = null,
    @SerialName("last_watered")
    val lastWatered: String? = null,
    @SerialName("last_fertilized")
    val lastFertilized: String? = null,
    @SerialName("custom_watering_interval")
    val customWateringInterval: Int? = null,
    @SerialName("custom_fertilization_interval")
    val customFertilizationInterval: Int? = null,
)
