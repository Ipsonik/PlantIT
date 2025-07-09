package com.example.plantit.features.plant_search.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PlantRequirement(
    @SerializedName("watering_interval")
    val watering : Int,
    @SerializedName("fertilizing_interval")
    val fertilization: Int,
    @SerializedName("light_requirement")
    val light: String,
    @SerializedName("temperature_min")
    val temperatureMin: Int,
    @SerializedName("temperature_max")
    val temperatureMax: Int,
    @SerializedName("soil_type")
    val soilType: String
)