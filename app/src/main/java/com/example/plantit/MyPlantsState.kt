package com.example.plantit

import com.example.plantit.model.Plant

data class MyPlantsState(
    val myPlants : List<Plant> = emptyList()
)
