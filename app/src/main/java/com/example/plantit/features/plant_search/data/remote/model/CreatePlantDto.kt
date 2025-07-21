package com.example.plantit.features.plant_search.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePlantDto(
    val name: String,
    val photo: String,
    @SerialName("watering_interval")     val watering: Int,
    @SerialName("fertilizing_interval") val fertilization: Int,
    @SerialName("light_requirement")    val light: String,
    @SerialName("temperature_min")      val temperatureMin: Int,
    @SerialName("temperature_max")      val temperatureMax: Int,
    @SerialName("soil_type")            val soilType: String)
