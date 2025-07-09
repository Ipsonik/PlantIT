package com.example.plantit.core.di

import com.example.plantit.core.data.network.KtorRemotePlantSearchDataSource
import com.example.plantit.core.data.network.RemotePlantSearchDataSource
import com.example.plantit.features.plant_detail.data.remote.network.KtorRemotePlantDetailDataSource
import com.example.plantit.features.plant_detail.data.remote.network.RemotePlantDetailDataSource
import com.example.plantit.features.plant_detail.data.repository.PlantDetailRepositoryImpl
import com.example.plantit.features.plant_detail.domain.repository.PlantDetailRepository
import com.example.plantit.features.plant_search.data.repository.PlantsRepositoryImpl
import com.example.plantit.features.plant_search.domain.repository.PlantsSearchRepository
import org.koin.dsl.module

val dataModule = module {
    single<RemotePlantSearchDataSource> { KtorRemotePlantSearchDataSource(get()) }

    single<RemotePlantDetailDataSource> { KtorRemotePlantDetailDataSource(get()) }

    single<PlantsSearchRepository> { PlantsRepositoryImpl(get()) }

    single<PlantDetailRepository> { PlantDetailRepositoryImpl(get()) }
}
