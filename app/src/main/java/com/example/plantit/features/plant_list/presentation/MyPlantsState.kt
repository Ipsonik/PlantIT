package com.example.plantit.features.plant_list.presentation

import com.example.plantit.features.plant_search.domain.model.Plant

data class MyPlantsState(
    val myPlants : List<Plant> = emptyList()
)