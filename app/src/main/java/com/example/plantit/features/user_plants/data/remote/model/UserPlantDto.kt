package com.example.plantit.features.user_plants.data.remote.model

import com.example.plantit.features.plant_search.data.remote.model.PlantDto
import com.example.plantit.features.user_plants.domain.model.UserPlant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPlantDto(
    val id: Int,
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
    val plant: PlantDto? = null

)

fun UserPlantDto.toUserPlant(): UserPlant {
    return UserPlant(
        id = id,
        plantId = plantId,
        userId = userId,
        customName = customName,
        lastWatered = lastWatered
            ?.takeIf { it.isNotBlank() }
            ?.let { LocalDate.parse(it) },
        lastFertilized = lastFertilized
            ?.takeIf { it.isNotBlank() }
            ?.let { LocalDate.parse(it) },
        customWateringInterval = customWateringInterval,
        customFertilizationInterval = customFertilizationInterval,
        photo = plant?.photo
    )
}

