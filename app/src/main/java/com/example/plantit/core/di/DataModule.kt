package com.example.plantit.core.di

import com.example.plantit.features.plant_search.data.remote.network.RemotePlantSearchDataSourceImpl
import com.example.plantit.features.plant_search.data.remote.network.RemotePlantSearchDataSource
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
import com.example.plantit.features.profile.data.remote.network.ProfileRemoteDataSource
import com.example.plantit.features.profile.data.remote.network.ProfileRemoteDataSourceImpl
import com.example.plantit.features.profile.data.repository.ProfileRepositoryImpl
import com.example.plantit.features.profile.domain.repository.ProfileRepository
import com.example.plantit.features.user_plants.data.remote.network.UserPlantRemoteDataSource
import com.example.plantit.features.user_plants.data.remote.network.UserPlantRemoteDataSourceImpl
import com.example.plantit.features.user_plants.data.repository.UserPlantRepositoryImpl
import com.example.plantit.features.user_plants.domain.repository.UserPlantRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    // DATA SOURCE
    single<RemotePlantSearchDataSource> { RemotePlantSearchDataSourceImpl(get(named("appClient"))) }

    single<RemotePlantDetailDataSource> { KtorRemotePlantDetailDataSource(get(named("appClient"))) }

    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(get(named("authClient"))) }

    single<UserPlantRemoteDataSource> { UserPlantRemoteDataSourceImpl(get(named("appClient"))) }

    single<ProfileRemoteDataSource> { ProfileRemoteDataSourceImpl(get(named("appClient"))) }


    // REPOSITORY
    single<PlantsSearchRepository> { PlantsRepositoryImpl(get()) }

    single<PlantDetailRepository> { PlantDetailRepositoryImpl(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<AuthStorage> { AuthStorageImpl(androidContext().dataStore) }

    single<UserPlantRepository> { UserPlantRepositoryImpl(get()) }

    single<ProfileRepository> { ProfileRepositoryImpl(get()) }

}
