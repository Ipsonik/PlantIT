package com.example.plantit.core.di

import com.example.plantit.core.data.network.KtorRemotePlantSearchDataSource
import com.example.plantit.core.data.network.RemotePlantSearchDataSource
import com.example.plantit.features.login.data.remote.local.AuthStorageImpl
import com.example.plantit.features.login.data.remote.network.AuthRemoteDataSource
import com.example.plantit.features.login.data.remote.network.AuthRemoteDataSourceImpl
import com.example.plantit.features.login.data.repository.AuthRepositoryImpl
import com.example.plantit.features.login.domain.AuthStorage
import com.example.plantit.features.login.domain.repository.AuthRepository
import com.example.plantit.features.plant_detail.data.remote.network.KtorRemotePlantDetailDataSource
import com.example.plantit.features.plant_detail.data.remote.network.RemotePlantDetailDataSource
import com.example.plantit.features.plant_detail.data.repository.PlantDetailRepositoryImpl
import com.example.plantit.features.plant_detail.domain.repository.PlantDetailRepository
import com.example.plantit.features.plant_search.data.repository.PlantsRepositoryImpl
import com.example.plantit.features.plant_search.domain.repository.PlantsSearchRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    // DATA SOURCE
    single<RemotePlantSearchDataSource> { KtorRemotePlantSearchDataSource(get()) }

    single<RemotePlantDetailDataSource> { KtorRemotePlantDetailDataSource(get()) }

    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(get()) }



    // REPOSITORY
    single<PlantsSearchRepository> { PlantsRepositoryImpl(get()) }

    single<PlantDetailRepository> { PlantDetailRepositoryImpl(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<AuthStorage> { AuthStorageImpl(androidContext().dataStore) }

}
