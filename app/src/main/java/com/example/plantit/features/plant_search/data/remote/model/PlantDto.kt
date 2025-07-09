package com.example.plantit.features.plant_search.data.remote.model

import com.example.plantit.features.plant_search.domain.model.Plant
import com.example.plantit.features.plant_search.domain.model.PlantRequirement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantDto(
    val id : Int,
    val name: String,
    val photo : String,
    @SerialName("watering_interval")
    val watering : Int,
    @SerialName("fertilizing_interval")
    val fertilization: Int,
    @SerialName("light_requirement")
    val light: String,
    @SerialName("temperature_min")
    val temperatureMin: Int,
    @SerialName("temperature_max")
    val temperatureMax: Int,
    @SerialName("soil_type")
    val soilType: String
)

fun PlantDto.toPlant(): Plant {
    return Plant(
        id = id,
        name = name,
        photo = photo,
        plantRequirement = PlantRequirement(
            watering = watering,
            fertilization = fertilization,
            light = light,
            temperatureMin = temperatureMin,
            temperatureMax = temperatureMax,
            soilType = soilType
        )
    )
}
