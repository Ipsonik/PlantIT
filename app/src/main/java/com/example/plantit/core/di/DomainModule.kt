package com.example.plantit.core.di

import com.example.plantit.features.plant_detail.domain.use_case.AddPlantToMyCollectionUseCase
import com.example.plantit.features.plant_detail.domain.use_case.GetPlantDetailByIdUseCase
import com.example.plantit.features.plant_search.domain.use_case.get_plants.GetAllPlantsUseCase
import com.example.plantit.features.plant_search.domain.use_case.search_plants.SearchPlantsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetAllPlantsUseCase(get()) }
    single { SearchPlantsUseCase(get()) }
    single { GetPlantDetailByIdUseCase(get()) }
    single { AddPlantToMyCollectionUseCase(get()) }
}
